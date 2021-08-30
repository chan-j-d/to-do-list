package storage;

import java.io.IOException;
import java.nio.file.Path;
import task.TaskList;

public class JsonStorageImpl extends JacksonStorageImpl<TaskList> implements Storage<TaskList> {

    @Override
    public boolean save(TaskList list, Path path) throws IOException {
        JsonTaskList jsonTaskList = JsonTaskList.convertToJson(list);
        return save(jsonTaskList, path);
    }

    @Override
    public TaskList load(Path path) throws IOException {
        return load(JsonTaskList.class, path);
    }

}
