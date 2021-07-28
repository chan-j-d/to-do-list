import parser.ParseException;
import parser.Parser;
import task.TaskList;
import util.Pair;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final String MESSAGE_ERROR_HEADER = "Error: ";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String input = scanner.next();
        Parser parser = new Parser();
        while (!input.equals("exit")) {
            try {
                Pair<String, String> inputPair = parser.parse(input);
                taskList.addTask(inputPair.getFirst(), inputPair.getSecond());
                System.out.println(taskList);
            } catch (ParseException pe){
                System.err.println(MESSAGE_ERROR_HEADER + pe.getMessage());
            }
            input = scanner.next();
        }
    }

}
