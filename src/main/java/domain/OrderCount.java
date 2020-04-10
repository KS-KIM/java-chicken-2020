package domain;

import java.util.Objects;

public class OrderCount {
	private static final int MIN_COUNT = 1;
	private static final int MAX_COUNT = 99;

	private final int count;

	public OrderCount(int count) {
		validateCountRange(count);
		this.count = count;
	}

	private void validateCountRange(int count) {
		if (count < MIN_COUNT || count > MAX_COUNT) {
			throw new IllegalArgumentException("주문 개수의 범위를 벗어났습니다.");
		}
	}

	public OrderCount add(OrderCount addedCount) {
		return new OrderCount(count + addedCount.count);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderCount that = (OrderCount)o;
		return count == that.count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}
}
