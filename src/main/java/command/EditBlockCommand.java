package command;

import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.TaskList;

public class EditBlockCommand implements Command<TaskList> {

    private static final String MESSAGE_INVALID_EDIT_BLOCK_INDEX = "%s is not a valid block for editing.";

    private final String oldHeader;
    private final String newHeader;

    public EditBlockCommand(String oldHeader, String newHeader) {
        this.oldHeader = oldHeader;
        this.newHeader = newHeader.strip();
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            taskList.editBlock(oldHeader, newHeader);
            return new EditedBlocksResult(taskList, oldHeader);
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(String.format(MESSAGE_INVALID_EDIT_BLOCK_INDEX, oldHeader));
        }
    }
}
