package command;

import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.Task;
import task.TaskList;

public class MoveTaskCommand implements Command<TaskList> {

    private final String fromBlockName;
    private final int index;
    private final String toBlockName;

    /**
     * Creates a new command that moves the task in {@code fromBlockName} at {@code index}
     * to {@code toBlockName}.
     */
    public MoveTaskCommand(String fromBlockName, int index, String toBlockName) {
        this.fromBlockName = fromBlockName;
        this.index = index;
        this.toBlockName = toBlockName;
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            Task taskToMove = taskList.deleteTask(fromBlockName, index);
            taskList.addTask(toBlockName, taskToMove.getDescription(), taskToMove.isDone());

            return new EditedBlocksResult(taskList, fromBlockName, toBlockName);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(ioobe.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MoveTaskCommand)) {
            return false;
        }

        MoveTaskCommand other = (MoveTaskCommand) o;
        return fromBlockName.equals(other.fromBlockName)
                && index == other.index
                && toBlockName.equals(other.toBlockName);
    }
}
