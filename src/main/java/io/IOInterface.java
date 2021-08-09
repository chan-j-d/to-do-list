package io;

import command.Command;
import task.TaskList;

public interface IOInterface {

    public Command<TaskList> getUserInput() throws InputException;
    public void updateUser(TaskList taskList);
    public void displayOnStartup(TaskList taskList);
    public void displayErrorMessage(String message);
    public void exit();

}
