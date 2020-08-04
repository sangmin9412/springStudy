package Main.service;

import java.util.Date;

import Main.DAO.MemberDao;
import Main.DTO.MemberDTO;
import Main.DTO.RegisterRequest;

public class MemberRegisterService {
	// ���� ��ü
	private MemberDao memberDao = new MemberDao();
	public void regist (RegisterRequest req) {
		MemberDTO dto = memberDao.selectByEmail(req.getEmail());
		if (dto == null) {
			dto = new MemberDTO();
			dto.setEmail(req.getEmail());
			dto.setName(req.getName());
			dto.setPassword(req.getPassword());
			dto.setRegisterDate(new Date());
			memberDao.insert(dto);
			System.out.println("����� ����� �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println("�ߺ� ������Դϴ�.");
		}
	}
	
}
