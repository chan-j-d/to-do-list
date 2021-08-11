package io.gui;

import command.Command;
import java.util.concurrent.Semaphore;
import task.TaskList;

public class CommandSync implements CommandMessenger {

    private Command<TaskList> userCommand;
    private final Semaphore semaphore;

    public CommandSync() {
        userCommand = null;
        semaphore = new Semaphore(0);
    }

    /**
     * Retrieves the next command from the user.
     */
    public Command<TaskList> getUserCommand() {
        try {
            semaphore.acquire();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return userCommand;
    }

    public void updateUserCommand(Command<TaskList> userCommand) {
        this.userCommand = userCommand;
        semaphore.release();
    }
}
