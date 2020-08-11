package service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import command.BoardCommand;
import model.BoardDTO;
import repository.BoardRepository;

public class BoardDeleteService {
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public String boardDelete(BoardCommand boardCommand, Errors errors) {
		String result = null;
		BoardDTO board = new BoardDTO();
		board.setBoardNum(boardCommand.getBoardNum());
		BoardDTO dto = boardRepository.getBoardList(board).get(0);
		if (bcryptPasswordEncoder.matches(boardCommand.getBoardPass(), dto.getBoardPass())) {
			boardRepository.deleteBoard(board);
			result = "redirect:/qna/boardList";
		} else {
			errors.rejectValue("boardPass", "badPass");
			result = "board/qna_board_modify";
		}
		return result;
	}
	
}
