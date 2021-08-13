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
    public boolean save(TaskList list, Path path) throws IOException {
        JsonTaskList jsonTaskList = JsonTaskList.convertToJson(list);

        boolean doesFileExist = Files.exists(path);
        Path parentDirectory = path.getParent();
        boolean doesParentDirectoryExist = Files.exists(parentDirectory);
        if (!doesFileExist && !doesParentDirectoryExist) {
            Files.createDirectory(parentDirectory);
        }

        objectMapper.writeValue(Paths.get(path.toString()).toFile(), jsonTaskList);
        return true;
    }

    @Override
    public TaskList load(Path path) throws IOException {
        JsonTaskList jsonTaskList = objectMapper.readValue(Paths.get(path.toString()).toFile(), JsonTaskList.class);
        return jsonTaskList.toJavaType();
    }

}
