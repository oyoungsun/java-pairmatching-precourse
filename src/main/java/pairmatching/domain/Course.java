package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    private Course(String name) {
        this.name = name;
    }

    public static Course findByString(final String course) {
        return Arrays.stream(Course.values()).filter(c -> c.name.equals(course)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("백엔드, 프론트엔드만 입력 가능합니다"));
    }

    public String getCourse() {
        return this.name;
    }

    // 추가 기능 구현
}