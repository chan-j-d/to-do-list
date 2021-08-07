package io.gui;

import command.Command;
import task.TaskList;

public interface CommandMessenger {

    public void updateUserCommand(Command<TaskList> userCommand);

}
