package io.gui;

import command.Command;

import java.util.List;
import java.util.concurrent.Semaphore;
import task.TaskList;

public class CommandSync implements CommandMessenger {

    private static final String MESSAGE_UNEXPECTED_ISSUE = "Unexpected issue encountered with acquiring user input.";

    private boolean isList = false;
    private List<Command<TaskList>> userCommands;

    private boolean isSingleCommand = false;
    private Command<TaskList> userCommand;

    private final Semaphore semaphore;

    public CommandSync() {
        userCommand = null;
        semaphore = new Semaphore(0);
    }

    /**
     * Retrieves the next command from the user.
     */
    public List<Command<TaskList>> getUserCommand() {
        try {
            semaphore.acquire();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        if (isList) {
            return userCommands;
        } else if (isSingleCommand) {
            return List.of(userCommand);
        } else {
            throw new RuntimeException(MESSAGE_UNEXPECTED_ISSUE);
        }
    }

    @Override
    public void updateUserCommand(Command<TaskList> userCommand) {
        this.userCommand = userCommand;
        isSingleCommand = true;
        isList = false;
        semaphore.release();
    }

    @Override
    public void updateUserCommands(List<Command<TaskList>> userCommands) {
        this.userCommands = userCommands;
        isList = true;
        isSingleCommand = false;
        semaphore.release();
    }
}
