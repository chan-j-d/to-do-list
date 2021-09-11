package command;

import command.result.CommandResult;
import command.result.DeletedBlockResult;
import task.TaskList;

public class DeleteBlockCommand implements Command<TaskList> {

    private static final String MESSAGE_INVALID_DELETE_BLOCK_INDEX = "%s is not a valid block to delete.";

    private final String blockName;

    public DeleteBlockCommand(String blockName) {
        this.blockName = blockName;
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            taskList.deleteBlock(blockName);
            return new DeletedBlockResult(taskList, blockName);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(String.format(MESSAGE_INVALID_DELETE_BLOCK_INDEX, ioobe.getMessage()));
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        }
    }

}
