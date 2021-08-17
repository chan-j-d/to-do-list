package command;

import java.util.Optional;
import task.TaskList;

public class AddBlockCommand implements Command<TaskList> {

    private final String blockName;
    private final Optional<Integer> optionalInteger;

    public AddBlockCommand(String blockName) {
        this.blockName = blockName;
        optionalInteger = Optional.empty();
    }

    public AddBlockCommand(int index, String blockName) {
        optionalInteger = Optional.of(index);
        this.blockName = blockName;
    }

    @Override
    public void run(TaskList taskList) throws CommandException {
        try {
            optionalInteger.ifPresentOrElse(index -> taskList.addBlock(index, blockName),
                    () -> taskList.addBlock(blockName));
        } catch (IllegalArgumentException iae) {
            throw new CommandException(iae.getMessage());
        }
    }
}
