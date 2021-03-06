package Main.service;

import java.util.Date;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;
import Main.DTO.RegisterRequest;

public class MemberRegisterService {
	// 의존 객체
	private MemberDao memberDao;
	
	// 의존객체 주입 : Dependency Injection(DI)
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void regist (RegisterRequest req) {
		MemberDTO dto = memberDao.selectByEmail(req.getEmail());
		if (dto == null) {
			dto = new MemberDTO();
			dto.setEmail(req.getEmail());
			dto.setName(req.getName());
			dto.setPassword(req.getPassword());
			dto.setRegisterDate(new Date());
			memberDao.insert(dto);
			System.out.println("사용자 등록이 완료되었습니다.");
		} else {
			System.out.println("중복 사용자입니다.");
		}
	}
	
}
