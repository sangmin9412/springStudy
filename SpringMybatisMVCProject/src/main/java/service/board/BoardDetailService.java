package service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.BoardDTO;
import repository.BoardRepository;

@Service
public class BoardDetailService {
	@Autowired
	BoardRepository boardRepository;
	public void boardDetail(Integer boardNum, Model model) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardNum(boardNum);
		List<BoardDTO> boardList = boardRepository.getBoardList(boardDTO);
		model.addAttribute("boardCommand", boardList.get(0));
	}
	
}
