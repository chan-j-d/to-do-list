package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskList {

    private static final String MESSAGE_INVALID_BLOCK_NAME = "%s is not a valid block name. " +
            "Avoid using days of the week.";
    private static final String MESSAGE_INVALID_BLOCK_INDEX = "%d is not a valid index as it is a day-of-week block.";

    private final Map<String, TaskBlock> blocks;
    private final List<String> keyOrder;

    /**
     * Creates a new empty {@code TaskList}.
     */
    public TaskList() {
        blocks = new HashMap<>();
        for (String blockName : BlockNames.DAYS) {
            blocks.put(blockName, new TaskBlock(blockName));
        }

        keyOrder = new ArrayList<>();
        keyOrder.addAll(BlockNames.DAYS);
    }

    /**
     * Creates a new {@code TaskList} object with the given {@code blocks} mapping
     * of blocknames to {@code TaskBlock}s.
     */
    public TaskList(List<String> keys, Map<String, TaskBlock> blocks) {
        keyOrder = new ArrayList<>();
        keyOrder.addAll(keys);

        this.blocks = new HashMap<>();
        for (String key : blocks.keySet()) {
            this.blocks.put(key, blocks.get(key));
        }
    }

    public void addTask(String blockName, String task) {
        blocks.get(blockName).addTask(task);
    }

    public void addTask(String blockName, String task, boolean isDone) {
        blocks.get(blockName).addTask(task, isDone);
    }

    public void addTask(String blockName, String task, int index) {
        blocks.get(blockName).addTask(task, index);
    }

    public void addTask(String blockName, String task, boolean isDone, int index) {
        blocks.get(blockName).addTask(task, isDone, index);
    }

    public void completeTask(String blockName, int index) {
        blocks.get(blockName).completeTask(index);
    }

    public void uncompleteTask(String blockName, int index) {
        blocks.get(blockName).uncompleteTask(index);
    }

    public Task deleteTask(String blockName, int index) {
        return blocks.get(blockName).deleteTask(index);
    }

    public Task getTask(String blockName, int index) {
        return blocks.get(blockName).getTask(index);
    }

    public List<Task> getTasksInBlock(String blockName) {
        return blocks.get(blockName).getTasks();
    }

    public TaskBlock getBlock(int index) {
        return blocks.get(keyOrder.get(index));
    }

    public TaskBlock getBlock(String blockName) {
        return blocks.get(blockName);
    }

    public List<String> getKeyOrder() {
        return keyOrder;
    }

    public Map<String, TaskBlock> getBlocksMap() {
        return this.blocks;
    }

    public int getNumTasksInBlock(String blockName) {
        return blocks.get(blockName).getNumTasks();
    }

    public int size() {
        return keyOrder.size();
    }

    public void addBlock(String blockName) {
        if (!BlockNames.isValidBlockName(blockName)) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_BLOCK_NAME, blockName));
        }

        keyOrder.add(blockName);
        blocks.put(blockName, new TaskBlock(blockName));
    }

    public void addBlock(int index, String blockName) {
        if (!BlockNames.isValidBlockName(blockName)) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_BLOCK_NAME, blockName));
        }

        keyOrder.add(index, blockName);
        blocks.put(blockName, new TaskBlock(blockName));
    }

    public void deleteBlock(int index) {
        String key = keyOrder.get(index); // Checks IndexOutOfBoundsException before deleting
        String blockName = blocks.get(key).getBlockName();
        if (BlockNames.DAYS.contains(blockName.toLowerCase())) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_BLOCK_INDEX, index));
        }
        blocks.remove(blockName);
        keyOrder.remove(index);
    }

    @Override
    public String toString() {
        return keyOrder.stream().reduce("", (x, y) -> x + "\n\n" + blocks.get(y),
                (x, y) -> x + "\n\n" + blocks.get(y));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaskList)) {
            return false;
        }

        TaskList list = (TaskList) o;
        return blocks.equals(list.blocks)
                && keyOrder.equals(list.keyOrder);
    }

}
