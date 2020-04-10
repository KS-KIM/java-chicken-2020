package controller;

import domain.Menu;
import domain.MenuRepository;
import domain.OrderCount;
import domain.Orders;
import domain.OrdersRepository;
import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

public class PosController {
	private static final int EXIT_SUCCESSFULLY = 0;

	public static void addOrder() {
		OutputView.printTables(TableRepository.tables(), OrdersRepository.getTableOrders());
		Table table = TableRepository.findTableById(InputView.inputTableNumber());

		OutputView.printMenus(MenuRepository.menus());
		Menu menu = MenuRepository.findMenuById(InputView.inputMenu());

		OrderCount orderCount = new OrderCount(InputView.inputOrderCount());

		OrdersRepository.insertOrUpdateOrder(table, menu, orderCount);
	}

	public static void payment() {
		OutputView.printTables(TableRepository.tables(), OrdersRepository.getTableOrders());
		Table table = TableRepository.findTableById(InputView.inputTableNumber());

		Orders orders = OrdersRepository.findOrdersBy(table);
		OutputView.printOrders(orders);
	}

	public static void exitProgram() {
		OutputView.printExitProgram();
		System.exit(EXIT_SUCCESSFULLY);
	}
}
