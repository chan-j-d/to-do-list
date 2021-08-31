package command.result;

import io.IoInterface;
import task.TaskList;

public class DeletedBlockResult implements CommandResult {

    private final int deletedIndex;
    private final TaskList taskList;

    public DeletedBlockResult(TaskList taskList, int deletedIndex) {
        this.taskList = taskList;
        this.deletedIndex = deletedIndex;
    }

    @Override
    public void updateInterface(IoInterface ioInterface) {
        ioInterface.removeBlock(taskList, deletedIndex);
    }
}
