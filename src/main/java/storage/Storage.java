package storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import task.TaskList;

public interface Storage<T> {

    /**
     * Saves the given {@code list} to the given {@code path}.
     * If {@code path} does not exist, creates a new save file.
     * If {@code path} exists, overwrites current file.
     */
    public boolean save(T list, Path path) throws IOException;

    /**
     * Loads the task list from the given {@code path}.
     */
    public T load(Path path) throws IOException;

}
