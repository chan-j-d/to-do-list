package storage;

import java.nio.file.Path;
import java.util.Optional;
import task.TaskList;

public interface Storage {

    /**
     * Saves the given {@code list} to the given {@code path}.
     * If {@code path} does not exist, creates a new save file.
     * If {@code path} exists, overwrites current file.
     */
    public boolean save(TaskList list, Path path);

    /**
     * Loads the task list from the given {@code path}.
     * If no available save file is found, returns an empty {@code Optional}.
     */
    public Optional<TaskList> load(Path path);

}
