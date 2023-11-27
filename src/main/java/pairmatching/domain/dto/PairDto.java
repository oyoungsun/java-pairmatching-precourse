package pairmatching.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PairDto {
    List<Set<String>> pairs;

    public PairDto(final List<Set<String>> pairs) {
        this.pairs = pairs;
    }

    public static PairDto fromEntity(List<Set<String>> pairs) {
        return new PairDto(pairs);
    }

    public List<Set<String>> getPairs() {
        return Collections.unmodifiableList(this.pairs);
    }
}
