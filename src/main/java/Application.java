import static controller.PosOperationMapper.EXIT_PROGRAM;
import static controller.PosOperationMapper.WAIT_STATUS;

import controller.PosOperationMapper;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		PosOperationMapper posOperationMapper = WAIT_STATUS;
		while (!EXIT_PROGRAM.equals(posOperationMapper)) {
			posOperationMapper = operate(posOperationMapper);
		}
	}

	private static PosOperationMapper operate(PosOperationMapper posOperationMapper) {
		try {
			posOperationMapper = PosOperationMapper.of(InputView.inputOperation());
			posOperationMapper.run();
		} catch (Exception e) {
			OutputView.printError(e.getMessage());
		}
		return posOperationMapper;
	}
}
