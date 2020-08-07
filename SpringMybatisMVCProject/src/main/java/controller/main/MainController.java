package controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.LoginCommand;

@Controller
public class MainController {
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public String mainForm(@ModelAttribute("loginCommand") LoginCommand loginCommand) {
		return "/main/main";
	}
}
