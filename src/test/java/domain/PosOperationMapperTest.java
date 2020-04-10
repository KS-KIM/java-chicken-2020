package domain;

import static controller.PosOperationMapper.WAIT_STATUS;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import controller.PosOperationMapper;

class PosOperationMapperTest {
	private static Stream<Arguments> generatePosOperation() {
		return Stream.of(
				Arguments.of(1, PosOperationMapper.ADD_ORDER),
				Arguments.of(2, PosOperationMapper.PAYMENT),
				Arguments.of(3, PosOperationMapper.EXIT_PROGRAM)
		);
	}

	@DisplayName("존재하는 메뉴를 입력하면 메뉴 타입을 반환")
	@MethodSource("generatePosOperation")
	@ParameterizedTest
	void of(int operationNumber, PosOperationMapper expect) {
		assertThat(PosOperationMapper.of(operationNumber)).isEqualTo(expect);
	}

	@DisplayName("존재하지 않는 메뉴 번호르 입력했을 때 WAIT_STATUS 반환")
	@ValueSource(ints = {-1, 0, 4})
	@ParameterizedTest
	void of_InvalidInputNumber_WaitStatus(int operationNumber) {
		assertThat(PosOperationMapper.of(operationNumber)).isEqualTo(WAIT_STATUS);
	}
}
