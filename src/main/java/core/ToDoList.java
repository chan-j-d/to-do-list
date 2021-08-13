package core;

import command.Command;
import command.CommandException;
import command.ExitCommand;
import io.InputException;
import io.IoInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import logging.LogsManager;
import storage.JsonStorageImpl;
import storage.SaveException;
import storage.Storage;
import task.TaskList;


public class ToDoList {

    private static final String SAVEFILE_DIRECTORY = "to-do-list";
    private static final String SAVEFILE_NAME = "todolist-save";
    private static final Path DEFAULT_SAVE_PATH = Paths.get(SAVEFILE_DIRECTORY, SAVEFILE_NAME);
    private static final String LOGFILE_NAME = "todolist-logs";
    private static final Path DEFAULT_LOG_PATH = Paths.get(SAVEFILE_DIRECTORY, LOGFILE_NAME);
    private static final String MESSAGE_NOT_SAVED = "Not saved properly.";

    private static final String ERROR_GETTING_INPUT = "Error encountered when retrieving user input.";
    private static final String ERROR_COMMAND_RUN = "Unable to run command: %s";
    private static final String ERROR_SAVING = "Unable to save to %s.";

    private final Storage storage;
    private final IoInterface ioInterface;
    private final TaskList taskList;
    private final Logger logger;
    private Path saveDirectory;

    /**
     * Creates a new {@code ToDoList} instance which utilizes the provided
     * {@code ioInterface} to communicate with the user.
     */
    public ToDoList(IoInterface ioInterface) {
        storage = new JsonStorageImpl();
        this.ioInterface = ioInterface;
        saveDirectory = DEFAULT_SAVE_PATH;
        taskList = storage.load(saveDirectory)
                .orElse(new TaskList());

        try {
            LogsManager.setLogFilePath(DEFAULT_LOG_PATH.toString());
        } catch (IOException ioe) {
            this.ioInterface.displayErrorMessage(ioe.getMessage());
        }
        logger = LogsManager.getLogger(this.getClass());
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
    public void runCommand(Command<TaskList> command) throws SaveException, CommandException {
        command.run(taskList);
        boolean isSaved = storage.save(taskList, saveDirectory);
        if (!isSaved) {
            throw new SaveException(MESSAGE_NOT_SAVED);
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
                logger.log(Level.WARNING, ERROR_GETTING_INPUT, ie);
                ioInterface.displayErrorMessage(ie.getMessage());
                continue;
            }

            try {
                runCommand(command);
                ioInterface.updateUser(taskList);
            } catch (CommandException ce) {
                logger.log(Level.WARNING, String.format(ERROR_COMMAND_RUN, command) ,ce);
                ioInterface.displayErrorMessage(ce.getMessage());
            } catch (SaveException se) {
                logger.log(Level.SEVERE, String.format(ERROR_SAVING, saveDirectory), se);
                ioInterface.displayErrorMessage(se.getMessage());
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
