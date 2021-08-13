package io;

import command.Command;
import task.TaskList;

import java.util.List;

public interface IoInterface {

    /**
     * Returns the current user request in the form of a {@code Command<TaskList>}.
     */
    public List<Command<TaskList>> getUserInput() throws InputException;

    /**
     * Updates the user's view with the change in {@code taskList}.
     */
    public void updateUser(TaskList taskList);

    /**
     * Displays view of {@code taskList} on startup.
     */
    public void displayOnStartup(TaskList taskList);

    /**
     * Displays error {@code message} to user.
     */
    public void displayErrorMessage(String message);

    /**
     * Performs any necessary housekeeping before exiting the program.
     */
    public void exit();

}
