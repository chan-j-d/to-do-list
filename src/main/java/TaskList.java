import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<TaskBlock> blocks;

    List<String> days = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    public TaskList() {
        blocks = new ArrayList<>();
        for (String day : days) {
            blocks.add(new TaskBlock(day));
        }
    }

    @Override
    public String toString() {
        return blocks.stream().reduce("", (x, y) -> x + "\n" + y, (x, y) -> x + "\n" + y);
    }

}
