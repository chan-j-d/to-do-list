package io.gui;

import command.Command;
import task.TaskList;

import java.util.concurrent.Semaphore;

public class CommandSync implements CommandMessenger {

    private Command<TaskList> userCommand;
    private final Semaphore semaphore;

    public CommandSync() {
        userCommand = null;
        semaphore = new Semaphore(0);
    }

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
