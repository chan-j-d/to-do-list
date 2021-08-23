package storage;

import gui.MainApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import settings.WindowSettings;
import task.TaskList;
import template.TaskListTemplate;
import util.FileUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSettingsStorageImplTest {

    private static final Path TEST_FILE_PATH_NO_DIRECTORY = Paths.get("build/test-save-directory/test-save");
    private static final Path TEST_FILE_PATH_WITH_DIRECTORY = Paths.get("build/test-save");

    private final Storage<WindowSettings> storage = new JsonSettingsStorageImpl();

    @Test
    public void saveAndLoad_normalTaskList_success() throws IOException {
        WindowSettings expectedWindowSettings = buildWindowSettingsTemplate();
        // directory already exists
        storage.save(expectedWindowSettings, TEST_FILE_PATH_WITH_DIRECTORY);

        WindowSettings actualWindowsSettings = storage.load(TEST_FILE_PATH_WITH_DIRECTORY);
        Assertions.assertEquals(expectedWindowSettings, actualWindowsSettings);
        FileUtil.deleteFile(TEST_FILE_PATH_WITH_DIRECTORY);

        //directory does not already exist
        storage.save(expectedWindowSettings, TEST_FILE_PATH_NO_DIRECTORY);

        actualWindowsSettings = storage.load(TEST_FILE_PATH_NO_DIRECTORY);
        Assertions.assertEquals(expectedWindowSettings, actualWindowsSettings);
        FileUtil.deleteFile(TEST_FILE_PATH_NO_DIRECTORY);
    }

    @Test
    public void load_noSaveFile_ioExceptionThrown() {
        Assertions.assertThrows(IOException.class, () -> storage.load(TEST_FILE_PATH_NO_DIRECTORY));
    }

    private WindowSettings buildWindowSettingsTemplate() {
        return new WindowSettings(345, 678);
    }
}
