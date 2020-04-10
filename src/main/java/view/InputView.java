package view;

import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {
	}

	public static int inputOperation() {
		System.out.println("##메인화면\n" +
				"1-주문등록\n" +
				"2-결제하기\n" +
				"3-프로그램종료\n\n" +
				"##원하는기능을선택하세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static int inputTableNumber() {
		System.out.println("## 테이블을 선택하세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static int inputMenu() {
		System.out.println("## 등록할 메뉴를 선택하세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public static int inputOrderCount() {
		System.out.println("## 메뉴의 수량을 입력하세요.");
		return Integer.parseInt(scanner.nextLine());
	}
}
