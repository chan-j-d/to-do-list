package command;

import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.TaskList;

public class EditBlockCommand implements Command<TaskList> {

    private static final String MESSAGE_INVALID_EDIT_BLOCK_INDEX = "%s is not a valid index for editing.";

    private final int index;
    private final String newHeader;

    public EditBlockCommand(int index, String newHeader) {
        this.index = index;
        this.newHeader = newHeader.strip();
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            taskList.editBlock(index, newHeader);
            return new EditedBlocksResult(taskList, index);
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(String.format(MESSAGE_INVALID_EDIT_BLOCK_INDEX, index));
        }
    }
}
