package command;

import command.result.CommandResult;

public interface Command<T> {

    /**
     * Runs a pre-built command on the provided object {@code t}.
     */
    public CommandResult run(T t) throws CommandException;

}
