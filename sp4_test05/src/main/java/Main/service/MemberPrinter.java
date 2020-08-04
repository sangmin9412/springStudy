package Main.service;

import Main.DTO.MemberDTO;

public class MemberPrinter {
	
	public void print (MemberDTO dto) {
		System.out.printf("회원정보 : 아이디 = %d, 비밀번호 = %s, 이메일 = %s, 이름 = %s, 생성날짜 = %tF \n", 
				dto.getId(),
				dto.getPassword(),
				dto.getEmail(),
				dto.getName(),
				dto.getRegisterDate());
	}
	
}
