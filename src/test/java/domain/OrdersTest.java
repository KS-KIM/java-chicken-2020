package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exception.OrderAddFailException;

class OrdersTest {
	@DisplayName("주문이 존재하지 않는 경우 주문을 추가")
	@Test
	void insertOrUpdateOrder_InsertOrder() {
		Orders orders = new Orders();

		Menu menu = MenuRepository.findMenuById(1).orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않음"));
		OrderCount orderCount = new OrderCount(99);
		orders.insertOrUpdateOrder(menu, orderCount);

		assertThat(orders.findOrderCountBy(menu)).isInstanceOf(OrderCount.class);
	}

	@DisplayName("주문이 이미 존재하는 경우 주문을 추가")
	@Test
	void insertOrUpdateOrder_UpdateOrder() {
		Orders orders = new Orders();

		Menu menu = MenuRepository.findMenuById(1).orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않음"));
		OrderCount orderCount = new OrderCount(40);
		orders.insertOrUpdateOrder(menu, orderCount);
		orders.insertOrUpdateOrder(menu, orderCount);

		assertThat(orders.findOrderCountBy(menu)).isInstanceOf(OrderCount.class);
	}

	@DisplayName("주문을 추가를 시도했을 때 개수를 초과하여 예외 발생")
	@Test
	void insertOrUpdateOrder_UpdateOrder_ExceptionThrown() {
		Orders orders = new Orders();

		Menu menu = MenuRepository.findMenuById(1).orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않음"));
		OrderCount orderCount1 = new OrderCount(50);
		orders.insertOrUpdateOrder(menu, orderCount1);

		OrderCount orderCount2 = new OrderCount(51);
		assertThatThrownBy(() -> orders.insertOrUpdateOrder(menu, orderCount2)).isInstanceOf(
				OrderAddFailException.class);
	}
}
