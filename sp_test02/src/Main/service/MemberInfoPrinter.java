package Main.service;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class MemberInfoPrinter {
	private MemberDao memberDao = new MemberDao();
	private MemberPrinter printer = new MemberPrinter(); 
	public void printMemberInfo(String arg) {
		MemberDTO dto = memberDao.selectByEmail(arg);
		if (dto == null) {
			System.out.println("데이터 없음");
			return;
		}
		printer.print(dto);
		
	}
	
	
}
