package command;

import command.result.CommandResult;
import command.result.EmptyCommandResult;
import task.TaskList;

public class ExitCommand implements Command<TaskList> {

    public CommandResult run(TaskList list) {
        return new EmptyCommandResult();
    }

}
