package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderCountTest {
	@DisplayName("1~99 사이의 수를 입력했을 때 주문 개수 생성")
	@ValueSource(ints = {1, 99})
	@ParameterizedTest
	void constructor(int count) {
		assertThat(new OrderCount(count)).isInstanceOf(OrderCount.class);
	}

	@DisplayName("주문 개수의 범위를 벗어나는 수를 입력했을 때 예외 발생")
	@ValueSource(ints = {-1, 0, 100})
	@ParameterizedTest
	void constructor_CountOutOfRange_ExceptionThrown(int count) {
		assertThatThrownBy(() -> new OrderCount(count)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("주문 개수를 추가할 수 있는 경우 기존 개수에 추가된 개수를 반환")
	@ValueSource(ints = {1, 98})
	@ParameterizedTest
	void addOrder(int count) {
		OrderCount orderCount = new OrderCount(1);
		OrderCount addedCount = new OrderCount(count);
		assertThat(orderCount.add(addedCount)).isInstanceOf(OrderCount.class);
	}

	@DisplayName("주문을 추가할 때 범위를 초과하는 경우 예외 발생")
	@ValueSource(ints = {5, 99})
	@ParameterizedTest
	void addOrder_CountOutOfRange_ExceptionThrown(int count) {
		OrderCount orderCount = new OrderCount(95);
		OrderCount addedCount = new OrderCount(count);
		assertThatThrownBy(() -> orderCount.add(addedCount)).isInstanceOf(IllegalArgumentException.class);
	}
}
