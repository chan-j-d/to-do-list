package command;

import command.result.AddedTaskResult;
import command.result.CommandResult;
import command.result.EditedBlocksResult;
import task.TaskList;

public class AddTaskCommand implements Command<TaskList> {

    private final String day;
    private final String taskDescription;

    public AddTaskCommand(String day, String taskDescription) {
        this.day = day;
        this.taskDescription = taskDescription;
    }

    public CommandResult run(TaskList list) {
        list.addTask(day, taskDescription);
        return new AddedTaskResult(list, day);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AddTaskCommand)) {
            return false;
        }
        AddTaskCommand other = (AddTaskCommand) o;
        return day.equals(other.day)
                && taskDescription.equals(other.taskDescription);
    }
}
