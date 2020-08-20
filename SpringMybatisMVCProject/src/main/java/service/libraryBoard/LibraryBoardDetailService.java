package service.libraryBoard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.AuthInfo;
import model.LibraryBoardDTO;
import model.StartEndPageDTO;
import repository.LibraryBoardRepository;

@Service
public class LibraryBoardDetailService {
	@Autowired
	LibraryBoardRepository libraryBoardRepository; 
	
	public void libDetail(Integer boardNum, Model model) {
		LibraryBoardDTO dto = new LibraryBoardDTO();
		
		dto.setBoardNum(boardNum);
		dto = libraryBoardRepository.getLibraryList(dto).get(0);
		String [] storeFileName = dto.getStoreFileName().split("`");
		String [] fileSize = dto.getFileSize().split("`");
		model.addAttribute("dto", dto);
		model.addAttribute("storeFileName", storeFileName);
		model.addAttribute("fileSize", fileSize);
		
	}

	public String libModify(Integer boardNum, Model model, String userId) {
		String result = null;
		LibraryBoardDTO dto = new LibraryBoardDTO();
		dto.setBoardNum(boardNum);
		dto.setUserId(userId);
		dto = libraryBoardRepository.getLibraryList(dto).get(0);
		
		if (dto != null) {
			model.addAttribute("libraryCommand", dto);
			String [] storeFileName = dto.getStoreFileName().split("`");
			String [] fileSize = dto.getFileSize().split("`");
			model.addAttribute("storeFileName", storeFileName);
			model.addAttribute("fileSize", fileSize);
			result = "lib_Board/lib_board_modify";
		} else {
			result = "redirect:/lib/libDetail/"+boardNum;
		}
		
		return result;
	}
	
}
