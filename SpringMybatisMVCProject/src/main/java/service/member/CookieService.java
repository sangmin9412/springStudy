package service.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import model.AuthInfo;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;

public class CookieService {
	@Autowired
	private MemberRepository memberRepository;
	private AuthInfo authInfo;
	
	public void getCookie(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cookie [] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c: cookies) {
				if (c.getName().startsWith("id")) {
					request.setAttribute("isId", c.getValue());
				}
				if (c.getName().startsWith("auto")) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setUserId(c.getValue());
					memberDTO.setStartEndPageDTO(new StartEndPageDTO());
					memberDTO.getStartEndPageDTO().setEndPage(1L);
					memberDTO.getStartEndPageDTO().setStartPage(1L);
					memberDTO = memberRepository.selectByMember(memberDTO);
					authInfo = new AuthInfo(
							memberDTO.getUserId(), memberDTO.getUserEmail(), 
							memberDTO.getUserName(), memberDTO.getUserPw()
							);
					session.setAttribute("authInfo", authInfo);
				}
			}
		}
		
	}
	
	
}
