package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {
	@DisplayName("메뉴 번호를 확인")
	@CsvSource(value = {"1,true", "2,false"})
	@ParameterizedTest
	void isSameNumber(int number, boolean expect) {
		Menu menu = MenuRepository.findMenuById(1);
		assertThat(menu.isSameNumber(number)).isEqualTo(expect);
	}

	@DisplayName("구입 개수를 입력받아 메뉴의 금액을 계산")
	@Test
	void calculatePrice() {
		Menu menu = MenuRepository.findMenuById(1);
		OrderCount orderCount = new OrderCount(5);
		assertThat(menu.calculatePrice(orderCount)).isEqualTo(80_000);
	}
}
