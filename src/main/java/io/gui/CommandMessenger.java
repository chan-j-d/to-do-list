package io.gui;

import command.Command;
import task.TaskList;

public interface CommandMessenger {

    /**
     *  Updates backend with current {@code userCommand}.
     */
    public void updateUserCommand(Command<TaskList> userCommand);

}
