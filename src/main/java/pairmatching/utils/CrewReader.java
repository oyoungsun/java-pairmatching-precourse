package pairmatching.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CrewReader {
    public static List<String> readCrewFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("유효하지 않은 파일 경로입니다.");
        }
    }
}
