package io.gui;

import command.Command;
import task.TaskList;

import java.util.List;

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
