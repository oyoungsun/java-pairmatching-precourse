package pairmatching.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PairInformationTest {

    @Test
    void createPairInformation() {
        PairInformation pairInformation = PairInformation.from("백엔드, 레벨1, 자동차경주");
        assertEquals(Course.BACKEND.getCourse(), pairInformation.getCourse().getCourse());
        assertEquals("자동차경주", pairInformation.getMission().getMission());
    }

    @ParameterizedTest
    @ValueSource(strings = {"모바일, 레벨1, 자동차경주", "백엔드, 레벨1,", "백엔드, , 자동차경주", "백엔드, 레벨7, 자동차경주", "백엔드, 레벨1, 배포"})
    void 잘못된_input으로_생성시_예외반환(String input){
        assertThrows(IllegalArgumentException.class, () -> PairInformation.from(input));
    }

}