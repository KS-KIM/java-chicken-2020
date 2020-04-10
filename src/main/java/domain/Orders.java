package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Orders orders1 = (Orders)o;
		return Objects.equals(orders, orders1.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orders);
	}
}
