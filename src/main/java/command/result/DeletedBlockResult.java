package command.result;

import io.IoInterface;
import task.TaskList;

public class DeletedBlockResult implements CommandResult {

    private final String deletedBlockName;
    private final TaskList taskList;

    public DeletedBlockResult(TaskList taskList, String deletedBlockName) {
        this.taskList = taskList;
        this.deletedBlockName = deletedBlockName;
    }

    @Override
    public void updateInterface(IoInterface ioInterface) {
        ioInterface.removeBlock(taskList, deletedBlockName);
    }
}
