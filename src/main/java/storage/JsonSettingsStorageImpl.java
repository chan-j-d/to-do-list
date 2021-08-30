package storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import settings.WindowSettings;

public class JsonSettingsStorageImpl extends JacksonStorageImpl<WindowSettings> implements Storage<WindowSettings> {

    @Override
    public boolean save(WindowSettings windowSettings, Path path) throws IOException {
        JsonWindowSettings jsonTaskList = JsonWindowSettings.convertToJson(windowSettings);
        return save(jsonTaskList, path);
    }

    @Override
    public WindowSettings load(Path path) throws IOException {
        return load(JsonWindowSettings.class, path);
    }
}
