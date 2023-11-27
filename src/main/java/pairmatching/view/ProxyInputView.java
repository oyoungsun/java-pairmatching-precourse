package pairmatching.view;

import pairmatching.utils.ExceptionHandler;

public class ProxyInputView implements Input {
    private final Input viewable;

    public ProxyInputView(Input viewable) {
        this.viewable = viewable;
    }

    @Override
    public String reqeustFeature() {
        return ExceptionHandler.input(viewable::reqeustFeature, 0);
    }

    @Override
    public String reqeustPairInformation() {
        return ExceptionHandler.input(viewable::reqeustPairInformation, 0);
    }

    @Override
    public String reqeustReMatching() {
        return ExceptionHandler.input(viewable::reqeustReMatching, 0);
    }
}
