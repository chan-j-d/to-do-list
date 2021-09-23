package command.result;

import io.IoInterface;
import task.TaskList;

public class EditedBlocksResult implements CommandResult {

    private final TaskList taskList;
    private final String[] editedBlockNames;

    public EditedBlocksResult(TaskList taskList, String... blockNames) {
        this.taskList = taskList;
        editedBlockNames = blockNames;
    }

    @Override
    public void updateInterface(IoInterface ioInterface) {
        for (String s : editedBlockNames) {
            ioInterface.updateUserBlock(taskList, s);
        }
    }
}
