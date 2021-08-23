package core;

import command.Command;
import command.CommandException;
import command.ExitCommand;
import io.InputException;
import io.IoInterface;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.LogsManager;
import storage.JsonStorageImpl;
import storage.Storage;
import task.TaskList;


public class ToDoList {

    private static final String SAVEFILE_NAME = "todolist-save";
    private static final String MESSAGE_NOT_LOADED_PROPERLY = "ToDoList was not loaded properly. Using a blank one.";

    private static final String ERROR_GETTING_INPUT = "Error encountered when retrieving user input.";
    private static final String ERROR_COMMAND_RUN = "Unable to run command: %s";
    private static final String ERROR_SAVING = "Unable to save to %s.";
    private static final String ERROR_UNEXPECTED_EXCEPTION =
            "Unexpected error encountered. Command was not run properly.";

    private final Storage<TaskList> storage;
    private final IoInterface ioInterface;
    private final TaskList taskList;
    private final Logger logger;
    private Path saveDirectory;

    /**
     * Creates a new {@code ToDoList} instance which utilizes the provided
     * {@code ioInterface} to communicate with the user.
     */
    public ToDoList(IoInterface ioInterface, Path savePath) {
        storage = new JsonStorageImpl();
        this.ioInterface = ioInterface;
        logger = LogsManager.getLogger(this.getClass());
        saveDirectory = savePath.resolve(SAVEFILE_NAME);
        taskList = loadTaskList(saveDirectory)
                .orElse(new TaskList());
    }

    private Optional<TaskList> loadTaskList(Path path) {
        try {
            return Optional.of(storage.load(path));
        } catch (IOException ie) {
            logger.log(Level.WARNING, MESSAGE_NOT_LOADED_PROPERLY, ie);
            ioInterface.displayErrorMessage(MESSAGE_NOT_LOADED_PROPERLY);
        }
        return Optional.empty();
    }

    /**
     * Sets the save path of the list to {@code path}.
     */
    public void setSavePath(Path path) {
        this.saveDirectory = path;
    }

    public Path getSavePath() {
        return saveDirectory;
    }

    /**
     * Runs the given {@code command} on its {@code taskList}.
     */
    public void runCommand(Command<TaskList> command) {
        try {
            command.run(taskList);
            storage.save(taskList, saveDirectory);
        } catch (IOException ie) {
            logger.log(Level.WARNING, ERROR_SAVING, ie);
            ioInterface.displayErrorMessage(ERROR_SAVING);
        } catch (CommandException ce) {
            logger.log(Level.WARNING, String.format(ERROR_COMMAND_RUN, command), ce);
            ioInterface.displayErrorMessage(ce.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_UNEXPECTED_EXCEPTION, e);
            ioInterface.displayErrorMessage(ERROR_UNEXPECTED_EXCEPTION);
        }
    }

    /**
     * Runs the program to constantly query for user commands until an
     * {@code ExitCommand} is given.
     */
    public void run() {
        ioInterface.displayOnStartup(taskList);
        List<Command<TaskList>> commands = null;
        boolean isExitCommand = false;
        while (!isExitCommand) {
            try {
                commands = ioInterface.getUserInput();
            } catch (InputException ie) {
                logger.log(Level.WARNING, ERROR_GETTING_INPUT, ie);
                ioInterface.displayErrorMessage(ie.getMessage());
                continue;
            }

            for (Command<TaskList> command : commands) {
                runCommand(command);
                ioInterface.updateUser(taskList);
                if (isExitCommand(command)) {
                    isExitCommand = true;
                }
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
