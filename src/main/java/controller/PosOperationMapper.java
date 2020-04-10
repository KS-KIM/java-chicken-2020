package controller;

import java.util.stream.Stream;

import view.OutputView;

public enum PosOperationMapper {
	ADD_ORDER(1, PosController::addOrder),
	PAYMENT(2, PosController::payment),
	EXIT_PROGRAM(3, PosController::exitProgram),
	WAIT_STATUS(0, OutputView::printRetryInputOperation);

	private final int number;
	private final Runnable operation;

	PosOperationMapper(int number, Runnable operation) {
		this.number = number;
		this.operation = operation;
	}

	public static PosOperationMapper of(int number) {
		return Stream.of(values())
				.filter(posOperationMapper -> posOperationMapper.number == number)
				.findFirst()
				.orElse(WAIT_STATUS);
	}

	public void run() {
		operation.run();
	}
}
