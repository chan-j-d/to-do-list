package command.result;

import io.IoInterface;
import task.TaskList;

public class AddedBlockResult implements CommandResult {

    private final int index;
    private final TaskList taskList;

    public AddedBlockResult(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void updateInterface(IoInterface ioInterface) {
        ioInterface.addBlock(taskList, index);
    }
}
