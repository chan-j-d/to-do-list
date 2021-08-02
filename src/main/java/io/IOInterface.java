package io;

import command.Command;
import task.TaskList;

public interface IOInterface {

    public Command<TaskList> getUserInput();
    public void updateUser();
    public void displayOnStartup();

}
