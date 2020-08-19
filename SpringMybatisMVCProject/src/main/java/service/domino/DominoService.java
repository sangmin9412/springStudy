package service.domino;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.ADTO;
import model.BDTO;
import model.CDTO;
import repository.DominoRepository;

@Service
public class DominoService {
	@Autowired
	DominoRepository dominoRepository;
	
	public void execute(Model model) {
		List<ADTO> list = dominoRepository.selectA();
		model.addAttribute("lists", list);
		
	}

	public void execute1(Model model, Integer num) {
		List<BDTO> list = dominoRepository.selectB(num);
		model.addAttribute("blists", list);
	}
	
	public void execute2(Model model, Integer aNum, Integer bNum) {
		BDTO dto = new BDTO();
		dto.setA1(aNum);
		dto.setB1(bNum);
		List<CDTO> list = dominoRepository.selectC(dto);
		model.addAttribute("clists", list);
	}
	
	
}
