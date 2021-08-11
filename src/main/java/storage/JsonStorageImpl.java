package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import task.TaskList;

public class JsonStorageImpl implements Storage {

    private final ObjectMapper objectMapper;

    public JsonStorageImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public boolean save(TaskList list, Path path) {
        JsonTaskList jsonTaskList = JsonTaskList.convertToJson(list);

        boolean doesFileExist = Files.exists(path);
        Path parentDirectory = path.getParent();
        boolean doesParentDirectoryExist = Files.exists(parentDirectory);
        if (!doesFileExist && !doesParentDirectoryExist) {
            try {
                Files.createDirectory(parentDirectory);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

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

    @Override
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
