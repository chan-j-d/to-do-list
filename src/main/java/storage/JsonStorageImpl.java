package storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class JsonStorageImpl implements Storage {

    private final ObjectMapper objectMapper;

    public JsonStorageImpl() {
        objectMapper = new ObjectMapper();
    }

    public boolean save(TaskList list, Path path) {
        JsonTaskList jsonTaskList = JsonTaskList.convertToJson(list);

        boolean isSaved;
        try {
            objectMapper.writeValue(Paths.get(path.toString()).toFile(), jsonTaskList);
            isSaved = true;
        } catch (IOException ioe) {
            isSaved = false;
            System.err.println(ioe);
        }
        return isSaved;
    }

    public Optional<TaskList> load(Path path) {
        Optional<TaskList> optionalTaskList = Optional.empty();
        try {
            JsonTaskList jsonTaskList = objectMapper.readValue(Paths.get(path.toString()).toFile(), JsonTaskList.class);
            optionalTaskList = Optional.of(jsonTaskList.toJavaType());
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return optionalTaskList;
    }

}
