import command.Command;
import command.CommandException;
import command.EmptyCommand;
import parser.ParseException;
import parser.Parser;
import storage.JsonStorageImpl;
import storage.Storage;
import task.TaskList;
import util.Pair;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ToDoList {

    private static final String SAVEFILE_NAME = "todolist-save";
    private static final Path DEFAULT_PATH = Paths.get(SAVEFILE_NAME);

    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;
    private Path saveDirectory;

    public ToDoList() {
        storage = new JsonStorageImpl();
        parser = new Parser();
        saveDirectory = DEFAULT_PATH;
        taskList = storage.load(saveDirectory)
                .orElse(new TaskList());
    }

    public void setSavePath(Path path) {
        this.saveDirectory = path;
    }

    public Command<TaskList> readInput(String input) {
        try {
            return parser.parse(input);
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        return new EmptyCommand();
    }

    public void runCommand(Command<TaskList> command) throws CommandException {
        command.run(taskList);
        storage.save(taskList, saveDirectory);
    }

    public void displayOutput() {
        System.out.println(taskList);
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        System.out.println(toDoList.taskList);
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String input = scanner.next();
        while (!input.equals("exit")) {
            Command<TaskList> command = toDoList.readInput(input);
            try {
                toDoList.runCommand(command);
            } catch (CommandException ce) {
                ce.printStackTrace();
            }
            toDoList.displayOutput();
            input = scanner.next();
        }
    }

}
