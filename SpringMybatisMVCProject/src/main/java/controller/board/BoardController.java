package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.BoardCommand;
import model.BoardDTO;
import service.board.BoardDeleteService;
import service.board.BoardDetailService;
import service.board.BoardListService;
import service.board.BoardModifyService;
import service.board.BoardWriteService;
import validator.BoardCommandValidator;

@Controller
@RequestMapping("qna")
public class BoardController {
	@Autowired
	BoardWriteService boardWriteService; 
	@Autowired
	BoardListService boardListService;
	@Autowired
	BoardDetailService boardDetailService;
	@Autowired
	BoardModifyService boardModifyService;
	@Autowired
	BoardDeleteService boardDeleteService;
	@RequestMapping(value="boardList", method = RequestMethod.GET)
	public String list(
			@RequestParam(value="page", defaultValue = "1") Integer page,
			Model model
			) {
		boardListService.boardSelect(page, model);
		return "board/qna_board_list";
	}
	@RequestMapping(value="boardWrite")
	public String boardWrite(BoardCommand boardCommand) {
		return "board/qna_board_write";
	}
	@RequestMapping(value="boardWritePro", method = RequestMethod.POST)
	public String boardWritePro(
				BoardCommand boardCommand,
				Errors errors,
				HttpSession session,
				HttpServletRequest request
			) {
		new BoardCommandValidator().validate(boardCommand, errors);
		if (errors.hasErrors()) {
			return "board/qna_board_list";
		}
		boardWriteService.boardInsert(boardCommand, session, request);
		return "redirect:/qna/boardList";
	}
	@RequestMapping(value="boardDetail")
	public String boardDetail(@RequestParam(value="boardNum") Integer boardNum, Model model) {
		boardDetailService.boardDetail(boardNum, model);
		return "board/qna_board_view";
	}
	@RequestMapping("boardModify")
	public String boardModify(@RequestParam(value="boardNum") Integer boardNum, BoardCommand boardCommand, Model model) {
		boardDetailService.boardDetail(boardNum, model);
		return "board/qna_board_modify";
	}
	@RequestMapping("boardModifyPro")
	public String boardModifyPro(
			@RequestParam(value="boardNum") Integer boardNum, 
			BoardCommand boardCommand,
			Errors errors,
			HttpSession session
			) {
		new BoardCommandValidator().validate(boardCommand, errors);
		if (errors.hasErrors()) {
			return "board/qna_board_modify";
		}
		
		return boardModifyService.boardUpdate(boardCommand, errors, session);
	}
	@RequestMapping("boardDelete")
	public String boardDelete(
			BoardCommand boardCommand,
			Errors errors
			) {
		new BoardCommandValidator().validate(boardCommand, errors);
		if (errors.hasErrors()) {
			return "board/qna_board_modify";
		}
		
		return boardDeleteService.boardDelete(boardCommand, errors);
	}
	
	@RequestMapping(value = "qnaDelete")
	public String boardDelete(
				@RequestParam("boardNum") Integer boardNum,
				@RequestParam("boardPass") String boardPass,
				Model model,
				HttpSession session
			) {
		return boardDeleteService.boardDelete(boardNum, boardPass, model, session);
	}
	
}
