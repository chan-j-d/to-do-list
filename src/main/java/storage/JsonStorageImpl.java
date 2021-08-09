package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
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
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public boolean save(TaskList list, Path path) {
        JsonTaskList jsonTaskList = JsonTaskList.convertToJson(list);

        boolean isSaved;
        try {
            objectMapper.writeValue(Paths.get(path.toString()).toFile(), jsonTaskList);
            isSaved = true;
        } catch (IOException ioe) {
            isSaved = false;
            ioe.printStackTrace();
        }
        return isSaved;
    }

    public Optional<TaskList> load(Path path) {
        Optional<TaskList> optionalTaskList;
        try {
            JsonTaskList jsonTaskList = objectMapper.readValue(Paths.get(path.toString()).toFile(), JsonTaskList.class);
            optionalTaskList = Optional.of(jsonTaskList.toJavaType());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            optionalTaskList = Optional.empty();
        }
        return optionalTaskList;
    }

}
