package command;

import java.util.Optional;

import command.result.AddedBlockResult;
import command.result.CommandResult;
import task.TaskList;

public class AddBlockCommand implements Command<TaskList> {

    private final String blockName;
    private final Optional<Integer> optionalInteger;

    public AddBlockCommand(String blockName) {
        this.blockName = blockName.strip();
        optionalInteger = Optional.empty();
    }

    public AddBlockCommand(int index, String blockName) {
        optionalInteger = Optional.of(index);
        this.blockName = blockName.strip();
    }

    @Override
    public CommandResult run(TaskList taskList) throws CommandException {
        try {
            optionalInteger.ifPresentOrElse(index -> taskList.addBlock(index, blockName),
                    () -> taskList.addBlock(blockName));
            int lastIndex = taskList.size();
            return optionalInteger.map(i -> new AddedBlockResult(taskList, i))
                    .orElse(new AddedBlockResult(taskList, lastIndex));
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        }
    }
}
