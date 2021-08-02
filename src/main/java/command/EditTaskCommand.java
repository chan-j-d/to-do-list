package command;

import task.Task;
import task.TaskList;

public class EditTaskCommand implements Command<TaskList> {

    private final String blockName;
    private final int index;
    private final String newDescription;

    public EditTaskCommand(String blockName, int index, String newDescription) {
        this.blockName = blockName;
        this.index = index;
        this.newDescription = newDescription;
    }

    @Override
    public void run(TaskList taskList) {
        Task oldTask = taskList.getTask(blockName, index);
        boolean isDone = oldTask.isDone();

        taskList.deleteTask(blockName, index);
        taskList.addTask(blockName, newDescription, isDone, index);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EditTaskCommand)) {
            return false;
        }

        EditTaskCommand other = (EditTaskCommand) o;
        return blockName.equals(other.blockName)
                && index == other.index
                && newDescription.equals(other.newDescription);
    }

}
