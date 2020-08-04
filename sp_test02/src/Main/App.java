package Main;

import java.util.Scanner;

import Main.DTO.RegisterRequest;
import Main.service.MemberListPrinter;
import Main.service.MemberRegisterService;

public class App {
	
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
				MemberRegisterService mrs = new MemberRegisterService();
				mrs.regist(req);
				
			} else if (command.startsWith("change")) {
				System.out.println("����");
				
			} else if (command.startsWith("list")) {
				System.out.println("����Ʈ");
				MemberListPrinter listPrint = new MemberListPrinter();
				listPrint.printAll();
				
			} else if (command.startsWith("info")) {
				System.out.println("����");
				
			} else if (command.startsWith("exit")) {
				System.out.println("���α׷��� ����Ǿ����ϴ�.");
				System.exit(0);
			}
		}
	}
	
	public static void printHelp() {
		System.out.println();
		System.out.println("��ɾ� ����:");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ���� ��� ������");
		System.out.println("list");
		System.out.println("info �̸���");
		System.out.println("���α׷� ����� exit");
	}
	
}
