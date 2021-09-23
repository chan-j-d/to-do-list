package command.result;

import io.IoInterface;

public class EmptyCommandResult implements CommandResult {

    @Override
    public void updateInterface(IoInterface ioInterface) {
        // do nothing
    }
}
