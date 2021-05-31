import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String input = scanner.next();
        while (!input.equals("exit")) {
            String[] details = input.replaceAll("\\s+", " ").split(" ");
            String day = details[0];
            String task = String.join(" ", Arrays.copyOfRange(details, 1, details.length));
            taskList.addTask(day, task);
            input = scanner.next();
        }


    }

}
