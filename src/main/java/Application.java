import static controller.PosOperationMapper.EXIT_PROGRAM;
import static controller.PosOperationMapper.WAIT_STATUS;

import controller.PosOperationMapper;
import view.InputView;

public class Application {
	public static void main(String[] args) {
		PosOperationMapper posOperationMapper = WAIT_STATUS;
		while (!EXIT_PROGRAM.equals(posOperationMapper)) {
			posOperationMapper = PosOperationMapper.of(InputView.inputOperation());
			posOperationMapper.run();
		}
	}
}
