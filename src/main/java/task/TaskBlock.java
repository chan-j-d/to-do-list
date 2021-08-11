package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskBlock {

    public static final String TITLE_SEPARATOR = "=============";
    public static final int STARTING_COUNT = 1;

    private String blockName;
    private List<Task> tasks;

    public TaskBlock(String blockName) {
        this.blockName = blockName;
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new {@code TaskBlock} with the given {@code blockName} and a
     * list of {@code tasks}.
     */
    public TaskBlock(String blockName, List<Task> tasks) {
        this.blockName = blockName;
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    public void addTask(String task) {
        tasks.add(new Task(task));
    }

    public void addTask(String task, boolean isDone) {
        tasks.add(new Task(task, isDone));
    }

    public void addTask(String task, int index) {
        tasks.add(adjustIndex(index), new Task(task));
    }

    public void addTask(String task, boolean isDone, int index) {
        tasks.add(adjustIndex(index), new Task(task, isDone));
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

    public int getNumTasks() {
        return tasks.size();
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

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {blockName, tasks});
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaskBlock)) {
            return false;
        }

        TaskBlock tb = (TaskBlock) o;
        return blockName.equals(tb.blockName)
                && tasks.equals(tb.tasks);
    }
}
