import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskList {

    Map<String, TaskBlock> blocks;

    List<String> days = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    public TaskList() {
        blocks = new HashMap<>();
        for (String day : days) {
            blocks.put(day, new TaskBlock(day));
        }
    }

    @Override
    public String toString() {
        return blocks.values().stream().reduce("", (x, y) -> x + "\n" + y, (x, y) -> x + "\n" + y);
    }

}
