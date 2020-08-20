package service.libraryBoard;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import command.FileInfo;
import command.LibraryCommand;
import model.AuthInfo;
import model.LibraryBoardDTO;
import repository.LibraryBoardRepository;

@Service
public class LibraryBoardModifyService {
	@Autowired
	LibraryBoardRepository libraryBoardRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	public String libraryUpdate(LibraryCommand libraryCommand, HttpSession session) {
		String result = null;
		LibraryBoardDTO dto = new LibraryBoardDTO();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		dto.setUserId(authInfo.getUserId());
		dto.setBoardNum(libraryCommand.getBoardNum());
		dto.setBoardContent(libraryCommand.getBoardContent());
		dto.setBoardSubject(libraryCommand.getBoardSubject());
		dto.setBoardPass(libraryCommand.getBoardPass());
		
		LibraryBoardDTO lib = libraryBoardRepository.getLibraryList(dto).get(0);
		List<FileInfo> list = (List<FileInfo>) session.getAttribute("fileList");
		
		System.out.println(list);
		
		if (list != null) {
			for (FileInfo fi : list) {
				lib.setOriginalFileName(lib.getOriginalFileName().replace(fi.getOriginalFileName()+"`", ""));
				lib.setStoreFileName(lib.getStoreFileName().replace(fi.getStoreFileName()+"`", ""));
				lib.setFileSize(lib.getFileSize().replace(fi.getFileSize()+"`", ""));
			}
		}
		
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		File file = null;
		String filePath = null;
		
		for (MultipartFile mf : libraryCommand.getReport()) {
			String original = mf.getOriginalFilename();
			String originalFileExtension = original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "") + originalFileExtension;
			String fileSize = Long.toString(mf.getSize());
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			// 파일저장
			String path1 = "WEB-INF/view/lib_Board/upload/";
			filePath = session.getServletContext().getRealPath(path1);
			file = new File(filePath + store);
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		dto.setOriginalFileName(originalTotal + lib.getOriginalFileName());
		dto.setStoreFileName(storeTotal + lib.getStoreFileName());
		dto.setFileSize(fileSizeTotal + lib.getFileSize());
		
		if (bcryptPasswordEncoder.matches(dto.getBoardPass(), lib.getBoardPass())) {
			libraryBoardRepository.libraryUpdate(dto);
			// 파일삭제
			if (list != null) {
				for (FileInfo fi : list) {
					file = new File(filePath + fi.getStoreFileName());
					if (file.exists()) {
						file.delete();
					}
				}
			}
			session.removeAttribute("fileList");
			result = "redirect:/lib/libDetail/" + dto.getBoardNum();
		} else {
			result = "redirect:/lib/libModify?boardNum=" + dto.getBoardNum();
			// result = "lib_board/lib_board_modify";
		}
		
		return result;
	}
	
}
