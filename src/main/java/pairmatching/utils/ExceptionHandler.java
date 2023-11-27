package pairmatching.utils;

import java.util.function.Supplier;
import pairmatching.view.OutputView;

public class ExceptionHandler {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final int MAX_RECUR_DEPTH = 10;

    public static <T> T input(Supplier<T> supplier, int depth) {
        if (depth >= MAX_RECUR_DEPTH) {
            throw new IllegalArgumentException(String.format("[ERROR] 입력 재시도 최대 가능한 %d회를 초과하였습니다.", MAX_RECUR_DEPTH));
        }
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return input(supplier, depth + 1);
        }
    }

    public static <T> T process(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return process(supplier);
        }
    }

    public static void printExceptionMessage(final IllegalArgumentException e) {
        OutputView.printExceptionMessage(String.format("%s %s", ERROR_PREFIX, e.getMessage()));
    }

}
