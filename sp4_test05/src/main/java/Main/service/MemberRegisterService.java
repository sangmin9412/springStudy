package Main.service;

import java.util.Date;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;
import Main.DTO.RegisterRequest;

public class MemberRegisterService {
	// 의존 객체
	private MemberDao memberDao;
	
	// 의존 객체 : Dependency Injection(DI)
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
			System.out.println("사용자가 등록되었습니다.");
		} else {
			System.out.println("중복된 사용자입니다.");
		}
	}
	
}
