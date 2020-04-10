package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PosOperationTest {
	private static Stream<Arguments> generatePosOperation() {
		return Stream.of(
				Arguments.of(1, PosOperation.ADD_ORDER),
				Arguments.of(2, PosOperation.PAYMENT),
				Arguments.of(3, PosOperation.EXIT_PROGRAM)
		);
	}

	@DisplayName("존재하는 메뉴를 입력하면 메뉴 타입을 반환")
	@MethodSource("generatePosOperation")
	@ParameterizedTest
	void of(int operationNumber, PosOperation expect) {
		assertThat(PosOperation.of(operationNumber)).isEqualTo(expect);
	}

	@DisplayName("존재하지 않는 메뉴 번호르 입력했을 때 예외 발생")
	@ValueSource(ints = {-1, 0, 4})
	@ParameterizedTest
	void of(int operationNumber) {
		assertThatThrownBy(() -> PosOperation.of(operationNumber)).isInstanceOf(IllegalArgumentException.class);
	}
}
