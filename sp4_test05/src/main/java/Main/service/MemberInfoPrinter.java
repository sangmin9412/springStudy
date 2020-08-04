package Main.service;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer; 
	
	// 의존 객체 : Dependency Injection(DI)
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// 의존 객체 : Dependency Injection(DI)
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String arg) {
		MemberDTO dto = memberDao.selectByEmail(arg);
		if (dto == null) {
			System.out.println("이메일이 없습니다");
			return;
		}
		printer.print(dto);
		
	}
	
	
}
