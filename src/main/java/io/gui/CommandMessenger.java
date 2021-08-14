package io.gui;

import command.Command;
import java.util.List;
import task.TaskList;

public interface CommandMessenger {

    /**
     *  Updates backend with current {@code userCommand}.
     */
    public void updateUserCommand(Command<TaskList> userCommand);

    /**
     * Updates backend with given {@code commands} executed sequentially.
     */
    public void updateUserCommands(List<Command<TaskList>> userCommands);

}
