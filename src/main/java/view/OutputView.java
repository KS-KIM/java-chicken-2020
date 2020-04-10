package view;

import java.util.List;
import java.util.Map;

import domain.Menu;
import domain.OrderCount;
import domain.Orders;
import domain.Table;

public class OutputView {
	private static final String TOP_LINE = "┌ ─ ┐";
	private static final String TABLE_FORMAT = "| %s |";
	private static final String BOTTOM_NOT_CASH_LINE = "└ ─ ┘";
	private static final String BOTTOM_CASH_LINE = "└ ₩ ┘";

	private OutputView() {
	}

	public static void printRetryInputOperation() {
		System.out.println("## 잘못 입력하셨습니다. 다시 입력하세요.");
	}

	public static void printTables(final List<Table> tables, final Map<Table, Orders> tableOrders) {
		System.out.println("## 테이블 목록");
		final int size = tables.size();
		printLine(TOP_LINE, size);
		printTableNumbers(tables);
		printBottomLine(tables, tableOrders);
	}

	public static void printOrders(Orders tableOrders) {
		System.out.println("메뉴 | 수량 | 금액");
		Map<Menu, OrderCount> orders = tableOrders.getOrders();
		for (Map.Entry<Menu, OrderCount> order : orders.entrySet()) {
			Menu menu = order.getKey();
			OrderCount orderCount = order.getValue();
			System.out.printf("%s | %d | %,d ₩\n", menu.getName(), orderCount.getCount(),
					menu.calculatePrice(orderCount.getCount()));
		}
		System.out.println();
	}

	public static void printError(String message) {
		System.err.println(message);
	}

	public static void printExitProgram() {
		System.out.println("## 프로그램을 종료합니다.");
	}

	public static void printMenus(final List<Menu> menus) {
		for (final Menu menu : menus) {
			System.out.println(menu);
		}
	}

	private static void printLine(final String line, final int count) {
		for (int index = 0; index < count; index++) {
			System.out.print(line);
		}
		System.out.println();
	}

	private static void printTableNumbers(final List<Table> tables) {
		for (final Table table : tables) {
			System.out.printf(TABLE_FORMAT, table);
		}
		System.out.println();
	}

	private static void printBottomLine(final List<Table> tables, final Map<Table, Orders> tableOrders) {
		for (final Table table : tables) {
			System.out.print(renderLine(table, tableOrders));
		}
		System.out.println();
	}

	private static String renderLine(final Table table, final Map<Table, Orders> tableOrders) {
		if (tableOrders.containsKey(table)) {
			return BOTTOM_CASH_LINE;
		}
		return BOTTOM_NOT_CASH_LINE;
	}
}
