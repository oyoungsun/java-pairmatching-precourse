package pairmatching.domain;


import java.util.Objects;
import java.util.regex.Pattern;

public class PairInformation {
    private static final String PAIR_INFORMATION = "^(백엔드|프론트엔드),\\s*레벨[1-5],\\s*(.*)$";
    private static final Pattern PAIR_INFORMATION_VALIDATOR = Pattern.compile(PAIR_INFORMATION);
    private static final String NOT_PAIR_PATTERN = "입력 형식이 잘못되었습니다. ex) 백엔드, 레벨1, 자동차경주";

    private final Mission mission;
    private final Course course;
    private final Level level;

    public PairInformation(final String course, final String level, final String mission) {
        this.mission = Mission.findByString(mission, level);
        this.level = this.mission.getLevel();
        this.course = Course.findByString(course);
    }

    public static PairInformation from(final String input) {
        if (!PAIR_INFORMATION_VALIDATOR.matcher(input).matches()) {
            throw new IllegalArgumentException(NOT_PAIR_PATTERN);
        }
        String[] splited = input.split(",");
        if (splited.length != 3) {
            throw new IllegalArgumentException(NOT_PAIR_PATTERN);
        }
        return new PairInformation(splited[0].trim(), splited[1].trim(), splited[2].trim());
    }

    public Course getCourse() {
        return course;
    }

    public Mission getMission() {
        return mission;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairInformation that = (PairInformation) o;
        return mission.equals(that.mission) && course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mission, course);
    }
}
