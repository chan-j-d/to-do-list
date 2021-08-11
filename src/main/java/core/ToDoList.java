package core;

import command.Command;
import command.CommandException;
import command.ExitCommand;
import io.InputException;
import io.IoInterface;
import java.nio.file.Path;
import java.nio.file.Paths;
import storage.JsonStorageImpl;
import storage.Storage;
import task.TaskList;


public class ToDoList {

    private static final String SAVEFILE_DIRECTORY = "to-do-list";
    private static final String SAVEFILE_NAME = "todolist-save";
    private static final Path DEFAULT_PATH = Paths.get(SAVEFILE_DIRECTORY, SAVEFILE_NAME);
    private static final String MESSAGE_NOT_SAVED = "Not saved properly.";

    private final Storage storage;
    private final IoInterface ioInterface;
    private final TaskList taskList;
    private Path saveDirectory;

    /**
     * Creates a new {@code ToDoList} instance which utilizes the provided
     * {@code ioInterface} to communicate with the user.
     */
    public ToDoList(IoInterface ioInterface) {
        storage = new JsonStorageImpl();
        this.ioInterface = ioInterface;
        saveDirectory = DEFAULT_PATH;
        taskList = storage.load(saveDirectory)
                .orElse(new TaskList());
    }

    /**
     * Sets the save path of the list to {@code path}.
     */
    public void setSavePath(Path path) {
        this.saveDirectory = path;
    }

    /**
     * Runs the given {@code command} on its {@code taskList}.
     */
    public void runCommand(Command<TaskList> command) throws CommandException {
        command.run(taskList);
        boolean isSaved = storage.save(taskList, saveDirectory);
        if (!isSaved) {
            throw new CommandException(MESSAGE_NOT_SAVED);
        }
    }

    /**
     * Runs the program to constantly query for user commands until an
     * {@code ExitCommand} is given.
     */
    public void run() {
        ioInterface.displayOnStartup(taskList);
        Command<TaskList> command = null;
        while (!isExitCommand(command)) {
            try {
                command = ioInterface.getUserInput();
            } catch (InputException ie) {
                ioInterface.displayErrorMessage(ie.getMessage());
                continue;
            }

            try {
                runCommand(command);
                ioInterface.updateUser(taskList);
            } catch (CommandException ce) {
                ioInterface.displayErrorMessage(ce.getMessage());
            }
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    private boolean isExitCommand(Command<TaskList> command) {
        return command instanceof ExitCommand;
    }

}
