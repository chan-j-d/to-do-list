package core;

import command.Command;
import command.ExitCommand;
import io.IOInterface;
import io.InputException;
import io.parser.Parser;
import command.CommandException;
import storage.JsonStorageImpl;
import storage.Storage;
import task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ToDoList {

    private static final String SAVEFILE_NAME = "todolist-save";
    private static final Path DEFAULT_PATH = Paths.get(SAVEFILE_NAME);

    private final Storage storage;
    private final IOInterface ioInterface;
    private final TaskList taskList;
    private Path saveDirectory;

    public ToDoList() {
        storage = new JsonStorageImpl();
        ioInterface = new Parser();
        saveDirectory = DEFAULT_PATH;
        taskList = storage.load(saveDirectory)
                .orElse(new TaskList());
    }

    public void setSavePath(Path path) {
        this.saveDirectory = path;
    }

    public void runCommand(Command<TaskList> command) throws CommandException {
        command.run(taskList);
        storage.save(taskList, saveDirectory);
    }

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

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.run();
    }

}
