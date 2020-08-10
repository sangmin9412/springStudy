package controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import command.MemberCommand;
import model.AuthInfo;
import service.member.FindPasswordService;
import service.member.MemberDetailService;
import service.member.MemberModifyService;
import service.member.MemberPasswordService;
import service.member.PasswordChangeService;
import validator.MemberModifyProVaildator;
import validator.MemberPasswordValidator;

@Controller
@RequestMapping("edit")
public class MemberEditController {
	@Autowired
	private MemberDetailService memberDetailService;
	@Autowired
	private MemberModifyService memberModifyService;
	@Autowired
	FindPasswordService findPasswordService;
	@Autowired
	PasswordChangeService passwordChangeService;
	@Autowired
	MemberPasswordService memberPasswordService;
	@RequestMapping("memberModify")
	public String memberModify(
			@RequestParam(value = "userId") String userId, Model model) {
		memberDetailService.memberDetail(userId, model);
		return "member/memberModify";
	}
	@RequestMapping("memberModifyPro")
	public String memberModifyPro(
			MemberCommand memberCommand, Errors errors,
			Model model) {
		new MemberModifyProVaildator().validate(memberCommand, errors);
		if(errors.hasErrors()) {
			return "member/memberModify";
		}
		String path = memberModifyService.memberUpdate(memberCommand, errors); 
		return path;		
	}
	@RequestMapping("findPassword")
	public String findPassword() {
		return "member/findPassword";
	}
	@RequestMapping("findPasswordPro")
	public String findPasswordPro(MemberCommand memberCommand, 
			Model model) {
		findPasswordService.findPass(memberCommand, model); 
		return "member/passView";
	}
	@RequestMapping("memberPwForm")
	public String memberPwForm(Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		model.addAttribute("userId", authInfo.getUserId());
		return "member/pwModify";
	}
	@RequestMapping("pwModify1")
	public String pwModify1(MemberCommand memberCommand,
			Model model) {
		String path = passwordChangeService.execute(memberCommand,model);
		return path;
	}
	@RequestMapping("pwModifyPro")
	public String pwModifyPro(MemberCommand memberCommand, 
			Errors errors, Model model,HttpSession session) {
		new MemberPasswordValidator().validate(memberCommand, errors);
		if(errors.hasErrors()) {
			return "member/pwModify_1";
		}
		memberPasswordService.execute(memberCommand, model, session);
		return "member/pwModifyOk";
	}
	
	
}
/// 스프링의 기본 원칙 : IOC , DI, AOP(공통기능구현)
/// 스프링부트 : IOC , DI, AutoConfigration , thymleaf