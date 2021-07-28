import task.TaskList;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String input = scanner.next();
        while (!input.equals("exit")) {
            taskList.addTask(day, taskString);
            System.out.println(taskList);
            input = scanner.next();
        }


    }

}
