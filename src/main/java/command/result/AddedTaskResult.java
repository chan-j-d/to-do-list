package command.result;

import io.IoInterface;
import task.TaskList;

public class AddedTaskResult extends EditedBlocksResult {

    private final String blockName;

    public AddedTaskResult(TaskList taskList, String blockName) {
        super(taskList, blockName);
        this.blockName = blockName;
    }

    @Override
    public void updateInterface(IoInterface ioInterface) {
        super.updateInterface(ioInterface);
        ioInterface.requestTextFieldFocus(blockName);
    }
}
