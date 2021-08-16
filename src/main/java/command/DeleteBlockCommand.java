package command;

import task.TaskList;

public class DeleteBlockCommand implements Command<TaskList> {

    private static final String MESSAGE_INVALID_DELETE_BLOCK_NAME = "%s is not a valid block name to delete."
            + "Days of the week cannot be deleted.";

    private final int index;

    public DeleteBlockCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList taskList) throws CommandException {
        try {
            taskList.deleteBlock(index);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(String.format(MESSAGE_INVALID_DELETE_BLOCK_NAME, ioobe.getMessage()));
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        }
    }

}
