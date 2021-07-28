package task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList {

    private final Map<String, TaskBlock> blocks;

    public static final List<String> DAYS = List.of("monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday", "sunday");

    public TaskList() {
        blocks = new LinkedHashMap<>();
        for (String day : DAYS) {
            blocks.put(day, new TaskBlock(day));
        }
    }

    public TaskList(Map<String, TaskBlock> blocks) {
        this.blocks = new LinkedHashMap<>();
        for (String key : blocks.keySet()) {
            this.blocks.put(key, blocks.get(key));
        }
    }

    public void addTask(String day, String task) {
        blocks.get(day).addTask(task);
    }

    @Override
    public String toString() {
        return blocks.values().stream().reduce("", (x, y) -> x + "\n\n" + y, (x, y) -> x + "\n\n" + y);
    }

}
