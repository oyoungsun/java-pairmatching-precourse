package pairmatching.service;

import static camp.nextstep.edu.missionutils.Randoms.shuffle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.PairInformation;


public class PairService {
    private static final int MATCHING_PEOPLE = 2;
    private final CrewRepository crewRepository;
    private HashMap<PairInformation, List<List<String>>> matched = new HashMap<>();

    private PairService(final CrewRepository repository) {
        this.crewRepository = repository;
    }

    public static PairService from(CrewRepository repository) {
        return new PairService(repository);
    }

    public void matching(final PairInformation pairInformation) {
        if (pairInformation.getCourse().equals(Course.BACKEND)) {
            matchingCrew(pairInformation, crewRepository.getBackend());
            return;
        }
        matchingCrew(pairInformation, crewRepository.getFrontend());
    }

    private void matchingCrew(final PairInformation pairInformation, final List<String> crew) {
        List<String> shuffled = shuffle(crew); //백엔드아님
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < shuffled.size()-1; i += MATCHING_PEOPLE) {
            List<String> match = new ArrayList<>();
            match.add(shuffled.get(i));
            match.add(shuffled.get(i + 1));
            result.add(match);
        }
        if (shuffled.size() % MATCHING_PEOPLE != 0) {
            result.get(result.size() - 1).add(shuffled.get(shuffled.size() - 1));
        }
        System.out.println(result.toString());
        matched.put(pairInformation, result);
    }

    public void ask() {
    }

    public void reset() {
    }

    public boolean isExistPair(final PairInformation pairInformation) {
        return matched.containsKey(pairInformation);
    }
}
