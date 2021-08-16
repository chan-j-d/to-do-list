package command;

import task.TaskList;

public class DeleteBlockCommand implements Command<TaskList> {

    private static final String MESSAGE_INVALID_DELETE_BLOCK_INDEX = "%s is not a valid index to delete.";

    private final int index;

    public DeleteBlockCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList taskList) throws CommandException {
        try {
            taskList.deleteBlock(index);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(String.format(MESSAGE_INVALID_DELETE_BLOCK_INDEX, ioobe.getMessage()));
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        }
    }

}
