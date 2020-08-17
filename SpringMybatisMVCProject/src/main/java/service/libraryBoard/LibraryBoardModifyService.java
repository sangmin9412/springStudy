package service.libraryBoard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		if (bcryptPasswordEncoder.matches(dto.getBoardPass(), lib.getBoardPass())) {
			libraryBoardRepository.libraryUpdate(dto);
			result = "redirect:/lib/libDetail/" + dto.getBoardNum();
		} else {
			result = "redirect:/lib/libModify?boardNum=" + dto.getBoardNum();
			// result = "lib_board/lib_board_modify";
		}
		
		return result;
	}
	
}
