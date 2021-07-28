import parser.ParseException;
import parser.Parser;
import storage.JsonStorageImpl;
import storage.Storage;
import task.Task;
import task.TaskList;
import util.Pair;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

public class ToDoList {

    private static final String MESSAGE_ERROR_HEADER = "Error: ";
    private static final String SAVEFILE_NAME = "todolist-save";
    private static final Path DEFAULT_PATH = Paths.get(SAVEFILE_NAME);

    private final Storage storage;
    private final Parser parser;
    private Path saveDirectory;
    private TaskList taskList;

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

    public void readInput(String input) {
        try {
            Pair<String, String> inputPair = parser.parse(input);
            taskList.addTask(inputPair.getFirst(), inputPair.getSecond());
            System.out.println(taskList);
        } catch (ParseException pe){
            System.err.println(MESSAGE_ERROR_HEADER + pe.getMessage());
        }
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String input = scanner.next();
        while (!input.equals("exit")) {
            toDoList.readInput(input);
            input = scanner.next();
        }
    }

}
