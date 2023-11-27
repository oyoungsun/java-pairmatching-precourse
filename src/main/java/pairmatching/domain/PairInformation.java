package pairmatching.domain;


public class PairInformation {
//    Level level;
//    Course course;

    public PairInformation(final String input) {
        //TODO
//        this.level = Level.from();
//        this.course = Course.from();
    }

    public static PairInformation from(final String input) {
        return new PairInformation(input);
    }
}
