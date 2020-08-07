package service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import command.LoginCommand;
import model.AuthInfo;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;

public class AuthService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder; 
	private AuthInfo authInfo; 
	
	public void authenticate(LoginCommand loginCommand, HttpSession session, Errors errors) {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(loginCommand.getUserId());
		memberDTO.setStartEndPageDTO(new StartEndPageDTO());
		memberDTO.getStartEndPageDTO().setStartPage(1L);
		memberDTO.getStartEndPageDTO().setEndPage(1L);
		memberDTO = memberRepository.selectByMember(memberDTO);
		if (memberDTO == null) {
			errors.rejectValue("userId", "notId"); // 아이디가 틀렸을 경우
		} else {
			if (bcryptPasswordEncoder.matches(loginCommand.getUserPw(), memberDTO.getUserPw())) {
				authInfo = new AuthInfo(
						memberDTO.getUserId(), memberDTO.getUserEmail(), 
						memberDTO.getUserName(), memberDTO.getUserPw());
				session.setAttribute("authInfo", authInfo);
			} else {
				errors.rejectValue("userPw", "wrong"); // 비밀번호가 틀렸을 경우
			}
		}
	}
	
}
