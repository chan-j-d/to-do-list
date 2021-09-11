package command;

import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.TaskList;

public class CompleteTaskCommand implements Command<TaskList> {

    private final String day;
    private final int index;

    public CompleteTaskCommand(String day, int index) {
        this.day = day;
        this.index = index;
    }

    @Override
    public CommandResult run(TaskList list) throws CommandException {
        try {
            list.completeTask(day, index);
            return new EditedBlocksResult(list, day);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(ioobe.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CompleteTaskCommand)) {
            return false;
        }
        CompleteTaskCommand other = (CompleteTaskCommand) o;
        return index == other.index
                && day.equals(other.day);
    }
}
