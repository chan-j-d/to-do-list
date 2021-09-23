package command;

import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.Task;
import task.TaskList;

public class EditTaskCommand implements Command<TaskList> {

    private final String blockName;
    private final int index;
    private final String newDescription;

    /**
     * Creates a new command that edits the task in {@code blockName} at {@code index}
     * to the new {@code newDescription}.
     */
    public EditTaskCommand(String blockName, int index, String newDescription) {
        this.blockName = blockName;
        this.index = index;
        this.newDescription = newDescription;
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            Task oldTask = taskList.getTask(blockName, index);
            boolean isDone = oldTask.isDone();
            taskList.addTask(blockName, newDescription, isDone, index);
            taskList.deleteTask(blockName, index + 1);

            return new EditedBlocksResult(taskList, blockName);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(ioobe.getMessage());
        }
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
