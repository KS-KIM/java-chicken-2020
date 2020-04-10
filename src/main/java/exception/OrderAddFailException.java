package exception;

public class OrderAddFailException extends RuntimeException {
	public OrderAddFailException() {
		super("주문 개수를 초과하여 추가할 수 없습니다.");
	}
}
