package storage;

import task.TaskList;

import java.nio.file.Path;
import java.util.Optional;

public interface Storage {

    public boolean save(TaskList list, Path path);
    public Optional<TaskList> load(Path path);

}
