package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	// localhost:8080/sp_test07/testHello
	@RequestMapping("/testHello")
	public String aaa(
				@RequestParam(value="name", defaultValue="이름이없어요") String name,
				@RequestParam(value="age", defaultValue="27") int age,
				Model model
			) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "hello";
	}
	
}
