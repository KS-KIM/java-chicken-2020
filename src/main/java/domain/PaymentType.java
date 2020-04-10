package domain;

import java.util.stream.Stream;

public enum PaymentType {
	CREDIT_CARD(1, 1.0),
	CASH(2, 0.95);

	private final int number;
	private final double discountRate;

	PaymentType(int number, double discountRate) {
		this.number = number;
		this.discountRate = discountRate;
	}

	public static PaymentType of(int number) {
		return Stream.of(values())
				.filter(paymentType -> paymentType.isSameNumber(number))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("결제 방식이 존재하지 않습니다."));
	}

	private boolean isSameNumber(int number) {
		return this.number == number;
	}

	public int applyDiscount(int cache) {
		return (int)(cache * discountRate);
	}
}
