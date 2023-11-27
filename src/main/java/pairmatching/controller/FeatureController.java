package pairmatching.controller;

import pairmatching.domain.Feature;
import pairmatching.service.PairService;
import pairmatching.utils.ExceptionHandler;
import pairmatching.view.Input;

public class FeatureController {
    private final Input inputView;
    private final PairService pairService;

    private FeatureController(final Input inputView, final PairService pairService) {
        this.inputView = inputView;
        this.pairService = pairService;
    }

    public static FeatureController of(final Input inputView, final PairService pairService) {
        return new FeatureController(inputView, pairService);
    }

    public void run() {
        while(true) {
            Feature feature = ExceptionHandler.process(() -> settingFeature());
            if(feature.isExit()) break;
            doFeature(feature);
        }
    }

    private void doFeature(final Feature feature) {
        if(feature.isMatching()){
            pairService.matching();
            return;
        }
        if(feature.isAsk()){
            pairService.ask();
            return;
        }
        pairService.reset();
    }

    private Feature settingFeature() {
        String input = ExceptionHandler.input(inputView::reqeustFeature, 0);
        return Feature.findFeatureByString(input);
    }
}
