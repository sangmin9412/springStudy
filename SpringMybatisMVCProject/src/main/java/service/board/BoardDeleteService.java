package service.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import command.BoardCommand;
import model.AuthInfo;
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
	
	public String boardDelete(Integer boardNum, String boardPass, Model model, HttpSession session) {
		String result = null;
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		BoardDTO board = new BoardDTO();
		board.setBoardNum(boardNum);
		board.setUserId(authInfo.getUserId());
		BoardDTO dto = boardRepository.getBoardList(board).get(0);
		if (bcryptPasswordEncoder.matches(boardPass, dto.getBoardPass())) {
			boardRepository.deleteBoard(board);
			result = "redirect:/qna/boardList";
		} else {
			model.addAttribute("err", "비밀번호가 틀렸습니다.");
			result = "board/qna_board_modify";
		}
		return result;
	}
	
}
