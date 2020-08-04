package Main.service;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer; 
	
	// ������ü ���� : Dependency Injection(DI)
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// ������ü ���� : Dependency Injection(DI)
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String arg) {
		MemberDTO dto = memberDao.selectByEmail(arg);
		if (dto == null) {
			System.out.println("������ ����");
			return;
		}
		printer.print(dto);
		
	}
	
	
}
