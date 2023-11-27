package pairmatching.controller;

import java.util.Collections;
import java.util.List;

public class PairDto {
    List<List<String>> pairs;

    public PairDto(final List<List<String>> pairs) {
        this.pairs = pairs;
    }

    public static PairDto fromEntity(List<List<String>> pairs) {
        return new PairDto(pairs);
    }

    public List<List<String>> getPairs() {
        return Collections.unmodifiableList(this.pairs);
    }
}
