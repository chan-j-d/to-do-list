package command;

public interface Command<T> {

    /**
     * Runs a pre-built command on the provided object {@code t}.
     */
    public void run(T t) throws CommandException;

}
