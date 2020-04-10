package domain;

import java.util.HashMap;
import java.util.Map;

public class OrdersRepository {
	private static final Map<Table, Orders> tableOrders = new HashMap<>();

	public static void insertOrUpdateOrder(Table table, Menu menu, OrderCount orderCount) {
		if (tableOrders.containsKey(table)) {
			updateOrder(table, menu, orderCount);
			return;
		}
		insertOrder(table, menu, orderCount);
	}

	private static void updateOrder(Table table, Menu menu, OrderCount orderCount) {
		Orders orders = tableOrders.get(table);
		orders.insertOrUpdateOrder(menu, orderCount);
	}

	private static void insertOrder(Table table, Menu menu, OrderCount orderCount) {
		Orders orders = new Orders();
		orders.insertOrUpdateOrder(menu, orderCount);
		tableOrders.put(table, orders);
	}

	public static Orders findOrdersBy(Table table) {
		return tableOrders.get(table);
	}
}
