package task;

import java.util.ArrayList;
import java.util.List;

public class TaskBlock {

    public static final String TITLE_SEPARATOR = "=============";

    private String blockName;
    private List<Task> tasks;

    public TaskBlock(String blockName) {
        this.blockName = blockName;
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    @Override
    public String toString() {
        return blockName + "\n" + TITLE_SEPARATOR +
                tasks.stream().reduce("",
                        (x, y) -> x + "\n" + y,
                        (x, y) -> x + "\n" + y);
    }

}
