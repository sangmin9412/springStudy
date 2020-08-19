package controller.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjaxController {
	@RequestMapping("/ajaxTest")
	public String ajaxTest() {
		return "ajax/AjaxTest";
	}
	
	@RequestMapping("/ajaxTest1")
	public String ajaxTest1() {
		return "ajax/AjaxTest1";
	}
	
	@RequestMapping("/ajaxTest2")
	public String ajaxTest2(
			@RequestParam(value = "num", defaultValue = "1") Integer num,
			Model model
			) {
		model.addAttribute("num" , num);
		return "ajax/AjaxTest2";
	}
	
}
