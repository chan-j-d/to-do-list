package command;

import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.TaskList;

public class DeleteTaskCommand implements Command<TaskList> {

    private final int index;
    private final String day;

    public DeleteTaskCommand(String day, int index) {
        this.day = day;
        this.index = index;
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            taskList.deleteTask(day, index);
            return new EditedBlocksResult(taskList, taskList.indexOf(day));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(ioobe.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DeleteTaskCommand)) {
            return false;
        }
        DeleteTaskCommand other = (DeleteTaskCommand) o;
        return index == other.index
                && day.equals(other.day);
    }
}
