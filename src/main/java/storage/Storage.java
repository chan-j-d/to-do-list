package storage;

import task.TaskList;

import java.nio.file.Path;

public interface Storage {

    public void save(TaskList list, Path path);
    public TaskList load(Path path);

}
