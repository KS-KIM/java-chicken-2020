package domain;

import java.util.Map;

public class PosCalculateRule {
	private static final int CHICKEN_DISCOUNT_UNIT = 10;
	private static final int DISCOUNT_PRICE = 10_000;

	private final Map<Menu, OrderCount> orders;

	public PosCalculateRule(Map<Menu, OrderCount> orders) {
		this.orders = orders;
	}

	public int calculate(PaymentType paymentType) {
		int chickenDiscountedPrice = calculateChickenDiscountedPrice();
		return paymentType.applyDiscount(chickenDiscountedPrice);
	}

	private int calculateChickenDiscountedPrice() {
		int totalPrice = calculateTotalPrice();
		int chickenDiscountPrice = calculateChickenDiscountPrice();
		return totalPrice - chickenDiscountPrice;
	}

	private int calculateTotalPrice() {
		return orders.entrySet()
				.stream()
				.mapToInt(menuOrder -> menuOrder.getKey().calculatePrice(menuOrder.getValue()))
				.sum();
	}

	private int calculateChickenDiscountPrice() {
		return countChicken() / CHICKEN_DISCOUNT_UNIT * DISCOUNT_PRICE;
	}

	private int countChicken() {
		return orders.entrySet()
				.stream()
				.filter(menuOrder -> menuOrder.getKey().isChicken())
				.mapToInt(menuOrder -> menuOrder.getValue().getCount())
				.sum();
	}
}
