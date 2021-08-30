package storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.TaskList;
import template.TaskListTemplate;
import util.FileUtil;
import util.TestUtil;

public class JsonStorageImplTest {

    private static final Path TEST_FILE_PATH_NO_DIRECTORY = Paths.get("build/test-save-directory/test-save");
    private static final Path TEST_FILE_PATH_WITH_DIRECTORY = Paths.get("build/test-save");
    private static final Path TEST_FILE_PATH_WITH_MISSING_KEY =
            Paths.get("build/resources/test/test-save-missing-key");
    private static final Path TEST_FILE_PATH_WITH_MISSING_BLOCK =
            Paths.get("build/resources/test/test-save-missing-block");

    private final Storage<TaskList> storage = new JsonStorageImpl();

    @BeforeAll
    public static void setLogsDirectory() {
        TestUtil.setLogsDirectory();
    }

    @Test
    public void saveAndLoad_normalTaskList_success() throws IOException {
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

    @Test
    public void load_saveWithOneMissingKey_loadsWithoutException() throws IOException {
        TaskList expectedList = TaskListTemplate.buildTaskListTemplate();
        TaskList taskList = storage.load(TEST_FILE_PATH_WITH_MISSING_KEY);
        Assertions.assertEquals(expectedList, taskList);
    }

    @Test
    public void load_saveWithOneMissingBlock_loadsWithoutException() throws IOException {
        TaskList expectedList = TaskListTemplate.buildTaskListTemplate();
        expectedList.deleteTask(TaskListTemplate.STRING_TEST_HEADER, 1); // the block is emptied
        TaskList taskList = storage.load(TEST_FILE_PATH_WITH_MISSING_BLOCK);
        Assertions.assertEquals(expectedList, taskList);
    }
}
