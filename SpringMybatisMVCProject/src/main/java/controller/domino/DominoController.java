package controller.domino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.domino.DominoService;

@Controller
public class DominoController {
	@Autowired
	DominoService dominoService;
	@RequestMapping("/domino")
	public String domino(Model model) {
		dominoService.execute(model);
		return "domino/dominoMain";
	}
	@RequestMapping("/domino2")
	public String domino2(
			@RequestParam(value = "num") Integer num,
			Model model
		) {
		dominoService.execute1(model, num);
		return "domino/domino2";
	}
	@RequestMapping("/domino3")
	public String domino3(
			@RequestParam(value = "aNum") Integer aNum,
			@RequestParam(value = "bNum") Integer bNum,
			Model model
		) {
		dominoService.execute2(model, aNum, bNum);
		return "domino/domino3";
	}
	
}
