package pairmatching.service;

import static camp.nextstep.edu.missionutils.Randoms.shuffle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pairmatching.controller.PairDto;
import pairmatching.domain.Course;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.Level;
import pairmatching.domain.PairInformation;


public class PairService {
    private static final int MATCHING_PEOPLE = 2;
    private static final int MAX_DEPTH = 3;
    private static int MINIMUM_MATCHING_SIZE = 2;
    private final CrewRepository crewRepository;
    private HashMap<PairInformation, List<Set<String>>> matched = new HashMap<>();
    private HashMap<Level, List<Set<String>>> dupplicated = new HashMap<>();


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
        List<Set<String>> result;
        int depth=0;
        if(crew.size() < MINIMUM_MATCHING_SIZE) throw new IllegalArgumentException("매칭이 불가능합니다.");
        do {
            if(depth == MAX_DEPTH) throw new IllegalArgumentException("매칭이 불가능합니다.");
            List<String> shuffled = shuffle(crew);
            result = shuffledPair(shuffled);
        }while(isPairDupplicated(pairInformation.getLevel(), result));
        matched.put(pairInformation, result);
    }

    private boolean isPairDupplicated(final Level level, final List<Set<String>> result) {
        List<Set<String>> joined = new ArrayList<>();
        if (dupplicated.containsKey(level)) {
            List<Set<String>> allPairsInLevel = dupplicated.get(level);
            for (Set<String> pair : result) {
                if (allPairsInLevel.contains(pair)) {
                    return true; // 중복된 경우
                }
            }
            joined.addAll(dupplicated.get(level));
        }
        joined.addAll(result);
        dupplicated.put(level, joined);
        return false;
    }
    private List<Set<String>> shuffledPair(final List<String> shuffled) {
        List<Set<String>> result = new ArrayList<>();
        for (int i = 0; i < shuffled.size() - 1; i += MATCHING_PEOPLE) {
            Set<String> match = new HashSet<>();
            match.add(shuffled.get(i));
            match.add(shuffled.get(i + 1));
            result.add(match);
        }
        if (shuffled.size() % MATCHING_PEOPLE != 0) {
            result.get(result.size() - 1).add(shuffled.get(shuffled.size() - 1));
        }
        return result;
    }

    public PairDto ask(final PairInformation pairInformation) {
        if (isExistPair(pairInformation)) {
            return getPairResult(pairInformation);
        }
        return null;
    }

    public void reset() {
        matched = new HashMap<>();
    }

    public boolean isExistPair(final PairInformation pairInformation) {
        return matched.containsKey(pairInformation);
    }

    public PairDto getPairResult(final PairInformation pairInformation) {
        return PairDto.fromEntity(matched.get(pairInformation));
    }
}
