package domain;

import java.util.HashMap;
import java.util.Map;

import exception.OrderAddFailException;

public class Orders {
	private final Map<Menu, OrderCount> orders;

	public Orders() {
		this.orders = new HashMap<>();
	}

	public void insertOrUpdateOrder(Menu menu, OrderCount count) {
		if (orders.containsKey(menu)) {
			updateOrder(menu, count);
			return;
		}
		insertOrder(menu, count);
	}

	private void updateOrder(Menu menu, OrderCount count) {
		try {
			OrderCount addedOrderCount = findOrderCountBy(menu).add(count);
			orders.put(menu, addedOrderCount);
		} catch (IllegalArgumentException exception) {
			throw new OrderAddFailException();
		}
	}

	private void insertOrder(Menu menu, OrderCount count) {
		orders.put(menu, count);
	}

	public OrderCount findOrderCountBy(Menu menu) {
		return orders.get(menu);
	}
}
