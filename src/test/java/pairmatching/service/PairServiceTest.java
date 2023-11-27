package pairmatching.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import pairmatching.domain.dto.PairDto;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.PairInformation;

class PairServiceTest {

    @Test
    void matching으로_크루를_매칭시킨다(){
        //given
        CrewRepository repository = new CrewRepository();
        PairService pairService = PairService.from(repository);
        PairInformation information = new PairInformation("백엔드", "레벨1", "자동차경주");
        //when
        pairService.matching(information);
        //then
        assertThat(pairService.getPairResult(information)).isInstanceOf(PairDto.class);
        assertThat(pairService.getPairResult(information).getPairs().size()).isEqualTo(repository.getBackend().size()/2);
    }

}