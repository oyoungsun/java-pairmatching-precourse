package pairmatching.domain;


import java.util.regex.Pattern;

public class PairInformation {
    private static final String PAIR_INFORMATION = "^(백엔드|프론트엔드),\\s*레벨[1-5],\\s*(.*)$";
    private static final Pattern PAIR_INFORMATION_VALIDATOR = Pattern.compile(PAIR_INFORMATION);

    private final Mission mission;
    private final Course course;

    public PairInformation(final String course, final String level, final String mission) {
        this.mission = Mission.findByString(mission, level);
        this.course = Course.findByString(course);
    }

    public static PairInformation from(final String input) {
        if (!PAIR_INFORMATION_VALIDATOR.matcher(input).matches()) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다. ex) 백엔드, 레벨1, 자동차경주");
        }
        String[] splited = input.split(",");
        return new PairInformation(splited[0].trim(), splited[1].trim(), splited[2].trim());
    }

    public String getCourse() {
        return course.getCourse();
    }
    public String getMission() {
        return mission.getMission();
    }
}
