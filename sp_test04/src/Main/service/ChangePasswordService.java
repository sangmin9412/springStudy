package Main.service;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao;
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public void changePassword(String email, String oldPassword, String newPassword) {
		MemberDTO dto = memberDao.selectByEmail(email);
		if (dto == null) {
			System.out.println("�̸����� �������� �ʽ��ϴ�.");
			return;
		}
		dto.changePassword(oldPassword, newPassword);
		memberDao.update(dto);
	}
	
}