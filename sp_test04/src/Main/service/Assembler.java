package Main.service;

import Main.DTO.MemberDao;

public class Assembler {
	private MemberDao memberDao;
	private MemberPrinter printer;
	private MemberRegisterService memberRegisterService;
	private MemberListPrinter memberListPrinter;
	private ChangePasswordService changePasswordService;
	private MemberInfoPrinter memberInfoPrinter;
	
	public Assembler() {
		this.memberDao = new MemberDao();
		this.printer = new MemberPrinter();
		this.memberRegisterService = new MemberRegisterService(memberDao);
		this.memberListPrinter = new MemberListPrinter(memberDao, printer);
		this.changePasswordService = new ChangePasswordService(memberDao);
		this.memberInfoPrinter = new MemberInfoPrinter();
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberPrinter getPrinter() {
		return printer;
	}

	public MemberRegisterService getMemberRegisterService() {
		return memberRegisterService;
	}

	public MemberListPrinter getMemberListPrinter() {
		return memberListPrinter;
	}

	public ChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	public MemberInfoPrinter getMemberInfoPrinter() {
		memberInfoPrinter.setMemberDao(memberDao);
		memberInfoPrinter.setMemberPrinter(printer);
		return memberInfoPrinter;
	}
	
}
