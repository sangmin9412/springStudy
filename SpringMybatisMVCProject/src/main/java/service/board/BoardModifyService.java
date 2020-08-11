package service.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import command.BoardCommand;
import model.AuthInfo;
import model.BoardDTO;
import repository.BoardRepository;

@Service
public class BoardModifyService {
	@Autowired
	BoardRepository boardRepository; 
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public String boardUpdate(BoardCommand boardCommand, Errors errors, HttpSession session) {
		String result = null;
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		BoardDTO board = new BoardDTO();
		board.setBoardNum(boardCommand.getBoardNum());
		board.setUserId(authInfo.getUserId());
		board.setBoardSubject(boardCommand.getBoardSubject());
		board.setBoardContent(boardCommand.getBoardContent());
		
		BoardDTO dto = boardRepository.getBoardList(board).get(0);
		if (bcryptPasswordEncoder.matches(boardCommand.getBoardPass(), dto.getBoardPass())) {
			boardRepository.updateBoard(board);
			result = "redirect:/qna/boardDetail?boardNum="+boardCommand.getBoardNum();
		} else {
			errors.rejectValue("boardPass", "badPass");
			result = "board/qna_board_modify";
		}
		return result;
	}
	
}
