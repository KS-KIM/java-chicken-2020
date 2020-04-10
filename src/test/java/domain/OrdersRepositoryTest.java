package domain;

import static domain.OrdersRepository.findOrdersBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersRepositoryTest {
	@DisplayName("테이블에 기존 주문이 있는 경우 테이블에 메뉴 추가")
	@Test
	void insertOrUpdateOrder_AlreadyExistTable() {
		Table table1 = TableRepository.findTableById(1);
		Menu menu1 = MenuRepository.findMenuById(2);
		OrderCount orderCount1 = new OrderCount(95);
		OrdersRepository.insertOrUpdateOrder(table1, menu1, orderCount1);

		Table table2 = TableRepository.findTableById(1);
		Menu menu2 = MenuRepository.findMenuById(1);
		OrderCount orderCount2 = new OrderCount(95);
		OrdersRepository.insertOrUpdateOrder(table2, menu2, orderCount2);

		Orders orders = new Orders();
		orders.insertOrUpdateOrder(menu1, orderCount1);
		orders.insertOrUpdateOrder(menu2, orderCount2);
		assertThat(findOrdersBy(table1)).isEqualTo(orders);
	}

	@DisplayName("테이블에 기존 주문이 없는 경우 테이블에 메뉴 추가")
	@Test
	void insertOrUpdateOrder_NotExistTable() {
		Table table = TableRepository.findTableById(2);
		Menu menu = MenuRepository.findMenuById(1);
		OrderCount orderCount = new OrderCount(95);
		OrdersRepository.insertOrUpdateOrder(table, menu, orderCount);

		Orders orders = new Orders();
		orders.insertOrUpdateOrder(menu, orderCount);
		assertThat(findOrdersBy(table)).isEqualTo(orders);
	}
}
