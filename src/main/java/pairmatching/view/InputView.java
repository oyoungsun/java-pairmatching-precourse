package pairmatching.view;


import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView implements Input {
    private static final InputView inputView = new InputView();
    private static final Pattern NUMBER = Pattern.compile("^[0-9]*$");
    private static final String ONLY_NUMBER = "숫자만 입력해주세요 ";
    private static final String INTEGER_BOUNDARY = "정수의 범위를 벗어났습니다. ";

    public static Input getInstance() {
        return new ProxyInputView(inputView);
    }

    @Override
    public String reqeustFeature() {
        return readString();
    }

    @Override
    public String reqeustPairInformation() {
        return readString();
    }

    @Override
    public String reqeustReMatching() {
        return readString();
    }

    private String readString() {
        return Console.readLine();
    }

    private int readInt(){
        String input = readString();
        Matcher matcher = NUMBER.matcher(input);
        if (!matcher.matches()) throw new IllegalArgumentException(ONLY_NUMBER);
        try{
            Integer.parseInt(input);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException(INTEGER_BOUNDARY);
        }
        return Integer.parseInt(input);
    }
}
