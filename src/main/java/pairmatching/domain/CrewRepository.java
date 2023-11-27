package pairmatching.domain;

import java.util.Collections;
import java.util.List;
import pairmatching.utils.CrewReader;

public class CrewRepository {
    private static final String FRONTEND_CREW_FILE_PATH = "src/main/resources/frontend-crew.md";
    private static final String BACKEND_CREW_FILE_PATH = "src/main/resources/backend-crew.md";
    private final List<String> backend;
    private final List<String> frontend;

    public CrewRepository() {
        this.backend = CrewReader.readCrewFile(BACKEND_CREW_FILE_PATH);
        this.frontend = CrewReader.readCrewFile(FRONTEND_CREW_FILE_PATH);
    }

    public List<String> getBackend() {
        return Collections.unmodifiableList(this.backend);
    }
    public List<String> getFrontend() {
        return Collections.unmodifiableList(this.frontend);
    }

}
