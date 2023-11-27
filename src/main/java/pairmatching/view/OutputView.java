package pairmatching.view;

public class OutputView {

    public static void printExceptionMessage(final String error) {
        System.out.println(error);
    }

    public static void printRequestPairInformation() {
        System.out.println(OutMessage.PAIR_MATCHING.getPrint());
    }
    public static void printPairSettingList() {
        printEmpty();
        System.out.println(OutMessage.PAIR_INFOMATION.getPrint());
    }

    public static void printRequestFeature() {
        printEmpty();
        System.out.println(OutMessage.FEATURE.getPrint());
    }

    public static void printEmpty() {
        System.out.println();
    }

    public static void printRequestReMatching() {
        System.out.println(OutMessage.REMATCHING.getPrint());
    }

    public static void printReset() {
        printEmpty();
        System.out.println(OutMessage.RESET_COMPLIETE.getPrint());
    }

    private enum OutMessage{
        //TODO : 레벨%d : %s, 레벨List.joining( | ) 로 변환
        RESET_COMPLIETE("초기화 되었습니다. "),
        REMATCHING("매칭 정보가 있습니다. 다시 매칭하시겠습니까?"),
        FEATURE("기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료"),
        PAIR_INFOMATION("#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################"),
        PAIR_MATCHING("과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주");

        private final String print;
        private OutMessage(final String print) {
            this.print = print;
        }
        private String getPrint() {
            return print;
        }

    }
}
