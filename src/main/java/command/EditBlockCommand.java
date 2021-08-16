package command;

import task.TaskList;

public class EditBlockCommand implements Command<TaskList> {

    private static final String MESSAGE_INVALID_EDIT_BLOCK_INDEX = "%s is not a valid index for editing.";

    private final int index;
    private final String newHeader;

    public EditBlockCommand(int index, String newHeader) {
        this.index = index;
        this.newHeader = newHeader;
    }

    @Override
    public void run(TaskList taskList) throws CommandException {
        try {
            taskList.editBlock(index, newHeader);
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(String.format(MESSAGE_INVALID_EDIT_BLOCK_INDEX, index));
        }
    }
}
