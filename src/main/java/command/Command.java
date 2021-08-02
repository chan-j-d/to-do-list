package command;

import task.TaskList;

public interface Command<T> {

    public void run(T t) throws CommandException;

}
