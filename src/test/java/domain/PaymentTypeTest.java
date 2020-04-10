package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PaymentTypeTest {
	@DisplayName("존재하는 결제 타입을 입력받아 반환")
	@CsvSource(value = {"1,CREDIT_CARD", "2,CASH"})
	@ParameterizedTest
	void of(int number, PaymentType expect) {
		assertThat(PaymentType.of(number)).isEqualTo(expect);
	}

	@DisplayName("존재하지 않는 결제 타입을 입력받아 예외 발생")
	@ValueSource(ints = {-1, 0, 3})
	@ParameterizedTest
	void of_InvalidNumber_ExceptionThrown(int number) {
		assertThatThrownBy(() -> PaymentType.of(number)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("결제 금액을 입력받아 할인을 반영한 금액 계산")
	@CsvSource(value = {"CREDIT_CARD,10000,10000", "CASH,10000,9500"})
	@ParameterizedTest
	void applyDiscount(PaymentType paymentType, int totalPrice, int expect) {
		assertThat(paymentType.applyDiscount(totalPrice)).isEqualTo(expect);

	}
}