package Main.service;

import java.util.Collection;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	// ������ü ���� : Dependency Injection(DI)
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<MemberDTO> lists =  memberDao.selectAll();
		System.out.println("�� ȸ�� ���� : " + lists.size());
		for (MemberDTO dto : lists) {
			printer.print(dto);
		}
	}
	
}
