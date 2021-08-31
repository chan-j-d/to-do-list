package command.result;

import io.IoInterface;
import task.TaskList;

public class EditedBlocksResult implements CommandResult {

    private final TaskList taskList;
    private final int[] indicesToEdit;

    public EditedBlocksResult(TaskList taskList, int... indices) {
        this.taskList = taskList;
        indicesToEdit = indices;
    }

    @Override
    public void updateInterface(IoInterface ioInterface) {
        for (int i : indicesToEdit) {
            ioInterface.updateUserBlock(taskList, i);
        }
    }
}
