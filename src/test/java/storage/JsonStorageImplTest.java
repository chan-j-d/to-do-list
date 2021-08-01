package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.TaskList;
import template.TaskListTemplate;
import util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class JsonStorageImplTest {

    private static final Path TEST_FILE_PATH = Paths.get("build/test-save");

    private final Storage storage = new JsonStorageImpl();

    @Test
    public void saveAndLost_normalTaskList_success() {
        TaskList expectedList = TaskListTemplate.buildTaskListTemplate();
        storage.save(expectedList, TEST_FILE_PATH);

        Optional<TaskList> optionalTaskList = storage.load(TEST_FILE_PATH);
        Assertions.assertTrue(optionalTaskList.isPresent());
        Assertions.assertEquals(expectedList, optionalTaskList.get());
        FileUtil.deleteFile(TEST_FILE_PATH);
    }
}
