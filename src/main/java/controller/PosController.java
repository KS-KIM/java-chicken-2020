package controller;

import domain.Menu;
import domain.MenuRepository;
import domain.OrderCount;
import domain.OrdersRepository;
import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

public class PosController {
	private static final int EXIT_SUCCESSFULLY = 0;

	public static void addOrder() {
		OutputView.printTables(TableRepository.tables());
		Table table = TableRepository.findTableById(InputView.inputTableNumber());

		OutputView.printMenus(MenuRepository.menus());
		Menu menu = MenuRepository.findMenuById(InputView.inputMenu());

		OrderCount orderCount = new OrderCount(InputView.inputOrderCount());

		OrdersRepository.insertOrUpdateOrder(table, menu, orderCount);
	}

	public static void payment() {

	}

	public static void exitProgram() {
		System.exit(EXIT_SUCCESSFULLY);
	}
}
