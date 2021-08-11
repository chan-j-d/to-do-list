package storage;

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

    private final Storage storage = new JsonStorageImpl();

    @Test
    public void saveAndLost_normalTaskList_success() {
        TaskList expectedList = TaskListTemplate.buildTaskListTemplate();
        // directory already exists
        storage.save(expectedList, TEST_FILE_PATH_WITH_DIRECTORY);

        Optional<TaskList> optionalTaskList = storage.load(TEST_FILE_PATH_WITH_DIRECTORY);
        Assertions.assertTrue(optionalTaskList.isPresent());
        Assertions.assertEquals(expectedList, optionalTaskList.get());
        FileUtil.deleteFile(TEST_FILE_PATH_WITH_DIRECTORY);

        //directory does not already exist
        storage.save(expectedList, TEST_FILE_PATH_NO_DIRECTORY);

        optionalTaskList = storage.load(TEST_FILE_PATH_NO_DIRECTORY);
        Assertions.assertTrue(optionalTaskList.isPresent());
        Assertions.assertEquals(expectedList, optionalTaskList.get());
        FileUtil.deleteFile(TEST_FILE_PATH_NO_DIRECTORY);
    }
}
