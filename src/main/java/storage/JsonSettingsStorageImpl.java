package storage;

import java.io.IOException;
import java.nio.file.Path;
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
