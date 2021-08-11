package task;

import java.util.LinkedHashMap;
import java.util.Map;

public class TaskList {

    private final Map<String, TaskBlock> blocks;

    /**
     * Creates a new empty {@code TaskList}.
     */
    public TaskList() {
        blocks = new LinkedHashMap<>();
        for (String day : BlockNames.BLOCK_NAMES) {
            blocks.put(day, new TaskBlock(day));
        }
    }

    /**
     * Creates a new {@code TaskList} object with the given {@code blocks} mapping
     * of blocknames to {@code TaskBlock}s.
     */
    public TaskList(Map<String, TaskBlock> blocks) {
        this.blocks = new LinkedHashMap<>();
        for (String key : blocks.keySet()) {
            this.blocks.put(key, blocks.get(key));
        }
    }

    public void addTask(String day, String task) {
        blocks.get(day).addTask(task);
    }

    public void addTask(String day, String task, boolean isDone) {
        blocks.get(day).addTask(task, isDone);
    }

    public void addTask(String day, String task, int index) {
        blocks.get(day).addTask(task, index);
    }

    public void addTask(String day, String task, boolean isDone, int index) {
        blocks.get(day).addTask(task, isDone, index);
    }

    public void completeTask(String blockName, int index) {
        blocks.get(blockName).completeTask(index);
    }

    public void uncompleteTask(String blockName, int index) {
        blocks.get(blockName).uncompleteTask(index);
    }

    public void deleteTask(String blockName, int index) {
        blocks.get(blockName).deleteTask(index);
    }

    public Task getTask(String blockName, int index) {
        return blocks.get(blockName).getTask(index);
    }

    public Map<String, TaskBlock> getBlocksMap() {
        return this.blocks;
    }

    public int getNumTasksInBlock(String blockName) {
        return blocks.get(blockName).getNumTasks();
    }

    @Override
    public String toString() {
        return blocks.values().stream().reduce("", (x, y) -> x + "\n\n" + y, (x, y) -> x + "\n\n" + y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaskList)) {
            return false;
        }

        TaskList list = (TaskList) o;
        return blocks.equals(list.blocks);
    }

}
