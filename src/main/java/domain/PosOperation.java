package domain;

import java.util.stream.Stream;

public enum PosOperation {
	ADD_ORDER(1),
	PAYMENT(2),
	EXIT_PROGRAM(3);

	private final int number;

	PosOperation(int number) {
		this.number = number;
	}

	public static PosOperation of(int number) {
		return Stream.of(values())
				.filter(posOperation -> posOperation.number == number)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
	}
}
