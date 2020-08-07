package service.member;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import command.MemberCommand;
import controller.MailService;
import controller.SmsSend;
import model.MemberDTO;
import repository.MemberRepository;

@Service
public class MemberJoinService {
	@Autowired
	MailService mailService;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	MemberRepository memberRepository;
	
	public Integer numUpdate(String num, String reciver, String userId ) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setJoinOk(num);
		memberDTO.setUserEmail(reciver);
		memberDTO.setUserId(userId);
		return memberRepository.joinOkUpdate(memberDTO);
	}
	
	public Integer execute(MemberCommand memberCommand) {
		Integer result = 0;
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(memberCommand.getUserId());
		memberDTO.setUserName(memberCommand.getUserName());
		memberDTO.setUserBirth(new Timestamp(memberCommand.getUserBirth().getTime()));
		memberDTO.setUserGender(memberCommand.getUserGender());
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		memberDTO.setUserEmail(memberCommand.getUserEmail());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		String interest = "";
		for (String s : memberCommand.getInterest()) {
			interest += s + "`";
		}
		memberDTO.setInterest(interest);
		memberDTO.setUserPw(bcryptPasswordEncoder.encode(memberCommand.getUserPw()));
		
		result = memberRepository.insertMember(memberDTO);
		if (result != null) {
			try {
				mailService.sendMail(memberDTO.getUserEmail(), memberDTO.getUserId());
				SmsSend ss = new SmsSend();
				ss.smsSend(memberDTO.getUserPh1(), memberDTO.getUserName()+"님 회원가입을 축하합니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}
