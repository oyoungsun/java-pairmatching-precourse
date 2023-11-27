package pairmatching.domain;

public class Rematching {
    public static final String YES = "네";
    public static final String NO = "아니오";

    public static boolean isYes(final String input) {
        return YES.equals(input);
    }

    public static boolean isNo(final String input) {
        return NO.equals(input);
    }
}
