package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    RACING_CAR("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    BASEBALL("숫자야구게임", Level.LEVEL1),
    BASKET("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAY("지하철노선도", Level.LEVEL2),
    IMPROVEMENT("성능개선", Level.LEVEL4),
    DEPLOY("배포", Level.LEVEL4);

    private final String name;
    private final Level level;

    private Mission(final String name, final Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission findByString(final String mission, final String level) {
        return Arrays.stream(Mission.values())
                .filter(m -> m.name.equals(mission) && m.level.getLevel().equals(level))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력 형식이 잘못되었습니다. ex) 백엔드, 레벨1, 자동차경주"));
    }

    public String getMission() {
        return this.name;
    }


}
