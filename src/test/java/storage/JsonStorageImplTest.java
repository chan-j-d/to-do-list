package storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.TaskList;
import template.TaskListTemplate;
import util.FileUtil;

public class JsonStorageImplTest {

    private static final Path TEST_FILE_PATH_NO_DIRECTORY = Paths.get("build/test-save-directory/test-save");
    private static final Path TEST_FILE_PATH_WITH_DIRECTORY = Paths.get("build/test-save");

    private final Storage<TaskList> storage = new JsonStorageImpl();

    @Test
    public void saveAndLost_normalTaskList_success() throws IOException {
        TaskList expectedList = TaskListTemplate.buildTaskListTemplate();
        // directory already exists
        storage.save(expectedList, TEST_FILE_PATH_WITH_DIRECTORY);

        TaskList taskList = storage.load(TEST_FILE_PATH_WITH_DIRECTORY);
        Assertions.assertEquals(expectedList, taskList);
        FileUtil.deleteFile(TEST_FILE_PATH_WITH_DIRECTORY);

        //directory does not already exist
        storage.save(expectedList, TEST_FILE_PATH_NO_DIRECTORY);

        taskList = storage.load(TEST_FILE_PATH_NO_DIRECTORY);
        Assertions.assertEquals(expectedList, taskList);
        FileUtil.deleteFile(TEST_FILE_PATH_NO_DIRECTORY);
    }
}
