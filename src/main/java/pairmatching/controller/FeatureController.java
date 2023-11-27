package pairmatching.controller;

import pairmatching.domain.Feature;
import pairmatching.domain.PairInformation;
import pairmatching.domain.Rematching;
import pairmatching.service.PairService;
import pairmatching.utils.ExceptionHandler;
import pairmatching.view.Input;
import pairmatching.view.OutputView;

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
        while (true) {
            Feature feature = ExceptionHandler.setting(() -> settingFeature());
            if (feature.isExit()) {
                break;
            }
            ExceptionHandler.process(() -> doFeature(feature));
        }
    }

    private void doFeature(final Feature feature) {
        if (feature.isMatching()) {
            matching();
            return;
        }
        if (feature.isAsk()) {
            OutputView.printPairInformationList();
            ExceptionHandler.process(() -> ask());
            return;
        }
        pairService.reset();
        OutputView.printReset();
    }

    private void ask() {
        PairInformation pairInformation = ExceptionHandler.setting(() -> settingPairInformation());
        PairDto pairDto = pairService.ask(pairInformation);
        if(pairDto == null){
            throw new IllegalArgumentException("매칭 이력이 없습니다.");
        }
        OutputView.printPairResult(pairDto);
    }

    private void matching() {
        OutputView.printPairInformationList();
        PairInformation pairInformation = doMatching();
        PairDto pairDto = pairService.getPairResult(pairInformation);
        OutputView.printPairResult(pairDto);
    }

    private PairInformation doMatching() {
        PairInformation pairInformation = ExceptionHandler.setting(() -> settingPairInformation());
        if (pairService.isExistPair(pairInformation)) {
            ExceptionHandler.process(() -> matchingAgain(pairInformation));
            return pairInformation;
        }
        pairService.matching(pairInformation);
        return pairInformation;
    }

    private void matchingAgain(final PairInformation pairInformation) {
        OutputView.printRequestReMatching();
        String input = inputView.reqeustReMatching();
        OutputView.printEmpty();
        if (Rematching.isYes(input)) {
            pairService.matching(pairInformation);
            return;
        }
        if (Rematching.isNo(input)) {
            doMatching();
        }
        throw new IllegalArgumentException("네 또는 아니오 중에서 입력해주세요.");
    }

    private PairInformation settingPairInformation() {
        OutputView.printRequestPairInformation();
        String input = ExceptionHandler.input(inputView::reqeustPairInformation, 0);
        return PairInformation.from(input);
    }

    private Feature settingFeature() {
        OutputView.printRequestFeature();
        String input = ExceptionHandler.input(inputView::reqeustFeature, 0);
        return Feature.findFeatureByString(input);
    }
}
