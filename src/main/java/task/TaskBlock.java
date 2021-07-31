package task;

import java.util.ArrayList;
import java.util.List;

public class TaskBlock {

    public static final String TITLE_SEPARATOR = "=============";
    private static final int STARTING_COUNT = 1;

    private String blockName;
    private List<Task> tasks;

    public TaskBlock(String blockName) {
        this.blockName = blockName;
        tasks = new ArrayList<>();
    }

    public TaskBlock(String blockName, List<Task> tasks) {
        this.blockName = blockName;
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    public String getBlockName() {
        return blockName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(adjustIndex(index));
    }

    public void completeTask(int index) {
        getTask(index).markDone();
    }

    public void uncompleteTask(int index) {
        getTask(index).markUndone();
    }

    public boolean deleteTask(int index) {
        Task taskToRemove = getTask(index);
        return tasks.remove(taskToRemove);
    }

    private int adjustIndex(int index) {
        if (index < STARTING_COUNT || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        return index - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(blockName + "\n" + TITLE_SEPARATOR);
        int count = STARTING_COUNT;
        for (Task task : tasks) {
            sb.append("\n" + count + ": " + task);
        }
        return sb.toString();
    }

}
