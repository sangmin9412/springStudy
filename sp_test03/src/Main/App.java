package Main;

import java.util.Scanner;

import Main.DTO.MemberDao;
import Main.DTO.RegisterRequest;
import Main.service.ChangePasswordService;
import Main.service.MemberInfoPrinter;
import Main.service.MemberListPrinter;
import Main.service.MemberPrinter;
import Main.service.MemberRegisterService;

public class App {
	private static MemberDao memberDao = new MemberDao();
	private static MemberPrinter printer = new MemberPrinter();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("��ɾ �Է��ϼ��� : ");
			String command = sc.nextLine();
			if (command.startsWith("new")) {
				// ���� ��ü - Ŭ���� �ȿ� �ִ� ��ü (dependency object)
				String [] arg = command.split(" ");
				if (arg.length != 5) {
					printHelp();
					System.out.println();
					continue;
				}
				// ���� ��ü
				RegisterRequest req = new RegisterRequest();
				req.setEmail(arg[1]);
				req.setName(arg[2]);
				req.setPassword(arg[3]);
				req.setConfirmPassword(arg[4]);
				boolean bl = req.isPasswordEqualConfirmPassword();
				if (!bl) {
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					continue;
				}
				// ���� ��ü
				MemberRegisterService mrs = new MemberRegisterService(memberDao);
				mrs.regist(req);
				
			} else if (command.startsWith("change")) {
				String [] arg = command.split(" ");
				if (arg.length != 4) {
					printHelp();
					continue;
				}
				ChangePasswordService changePwdSvc = new ChangePasswordService(memberDao);
				changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
				
			} else if (command.startsWith("list")) {
				MemberListPrinter listPrint = new MemberListPrinter(memberDao, printer);
				listPrint.printAll();
				
			} else if (command.startsWith("info")) {
				String [] arg = command.split(" ");
				if (arg.length != 2) {
					printHelp();
					continue;
				}
				MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
				infoPrinter.setMemberDao(memberDao);
				infoPrinter.setMemberPrinter(printer);
				infoPrinter.printMemberInfo(arg[1]);
				
			} else if (command.startsWith("exit")) {
				System.out.println("���α׷��� ����Ǿ����ϴ�.");
				System.exit(0);
				
			} else {
				printHelp();
			}
		}
	}
	
	public static void printHelp() {
		System.out.println();
		System.out.println("��ɾ� ����:");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ������ ������");
		System.out.println("list");
		System.out.println("info �̸���");
		System.out.println("���α׷� ����� exit");
	}
	
}
