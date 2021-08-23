package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import settings.WindowSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSettingsStorageImpl implements Storage<WindowSettings> {

    private final ObjectMapper objectMapper;

    public JsonSettingsStorageImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public boolean save(WindowSettings windowSettings, Path path) throws IOException {
        JsonWindowSettings jsonTaskList = JsonWindowSettings.convertToJson(windowSettings);

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
    public WindowSettings load(Path path) throws IOException {
        JsonWindowSettings jsonWindowSettings = objectMapper.readValue(Paths.get(path.toString()).toFile(),
                JsonWindowSettings.class);
        return jsonWindowSettings.toJavaType();
    }
}
