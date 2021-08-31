package io;

import command.Command;
import java.util.List;
import task.TaskList;

public interface IoInterface {

    /**
     * Returns the current user request in the form of a {@code Command<TaskList>}.
     */
    public List<Command<TaskList>> getUserInput() throws InputException;

    /**
     * Updates the user's view with the change in {@code taskList}.
     */
    public void updateUserBlock(TaskList taskList, int index);

    public void removeBlock(TaskList taskList, int index);

    public void addBlock(TaskList taskList, int index);

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
