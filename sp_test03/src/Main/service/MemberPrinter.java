package Main.service;

import Main.DTO.MemberDTO;

public class MemberPrinter {
	
	public void print (MemberDTO dto) {
		System.out.printf("ȸ�������� : ���̵� = %d, ��й�ȣ = %s, �̸��� = %s, �̸� = %s, ����� = %tF \n", 
				dto.getId(),
				dto.getPassword(),
				dto.getEmail(),
				dto.getName(),
				dto.getRegisterDate());
	}
	
}
