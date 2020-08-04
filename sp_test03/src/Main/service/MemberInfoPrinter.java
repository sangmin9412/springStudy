package Main.service;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer; 
	
	// 의존객체 주입 : Dependency Injection(DI)
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// 의존객체 주입 : Dependency Injection(DI)
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String arg) {
		MemberDTO dto = memberDao.selectByEmail(arg);
		if (dto == null) {
			System.out.println("데이터 없음");
			return;
		}
		printer.print(dto);
		
	}
	
	
}
