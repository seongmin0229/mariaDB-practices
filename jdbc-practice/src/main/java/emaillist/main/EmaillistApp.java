package emaillist.main;

import java.util.List;
import java.util.Scanner;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistApp {
	private static int listCount = 0;
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		while(true) {
			System.out.println("(l)ist (d)elete (i)nsert (q)uit > ");
			String command = scanner.nextLine();
			initCount();
			if("q".equals(command)) {
				break;
			}else if("l".equals(command)) {
				doList();
			}else if("d".equals(command)) {
				doDelete();
			}else if("i".equals(command)) {
				doInsert();
			}
		}
	}

	private static void initCount() {
		listCount = new EmaillistDao().rowCount();
	}

	private static void doInsert() {
		System.out.print("성 : ");
		String firstName = scanner.nextLine();
		System.out.print("이름 : ");
		String lastName = scanner.nextLine();
		System.out.print("이메일 : ");
		String email = scanner.nextLine();
		EmaillistVo insertvo = new EmaillistVo();
		insertvo.setFirstName(firstName);
		insertvo.setLastName(lastName);
		insertvo.setEmail(email);
		new EmaillistDao().insert(insertvo);
	}

	private static void doDelete() {
		System.out.print("삭제할 대상의 이메일 > ");
		String email = scanner.nextLine();
		new EmaillistDao().deleteByEmail(email);
	}

	private static void doList() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVo vo : list) {
			System.out.println(vo.getFirstName() + vo.getLastName() + " - " + vo.getEmail());
		}
	}

}
