package pairmatching.domain;

import java.util.Arrays;

public enum Feature {
    MATCHING("1"),
    ASK("2"),
    RESET("3"),
    EXIT("Q");

    private final String feature;

    private Feature(final String feature) {
        this.feature = feature;
    }

    public static Feature findFeatureByString(final String input) {
        return Arrays.stream(Feature.values())
                .filter(feat -> feat.feature.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("1, 2, 3, Q로 입력 가능합니다"));

    }

    public boolean isExit() {
        return this.feature.equals(EXIT.feature);
    }

    public boolean isMatching() {
        return this.feature.equals(MATCHING.feature);
    }

    public boolean isAsk() {
        return this.feature.equals(ASK.feature);
    }
}
