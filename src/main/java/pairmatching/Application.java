package pairmatching;

import pairmatching.controller.FeatureController;
import pairmatching.domain.CrewRepository;
import pairmatching.service.PairService;
import pairmatching.view.Input;
import pairmatching.view.InputView;

public class Application {
    public static void main(String[] args) {
        Input input = InputView.getInstance();
        PairService service = PairService.from(new CrewRepository());
        FeatureController controller = FeatureController.of(input, service);
        controller.run();
    }
}
