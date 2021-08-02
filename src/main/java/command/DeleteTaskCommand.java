package command;

import task.TaskList;

public class DeleteTaskCommand implements Command<TaskList> {

    private final int index;
    private final String day;

    public DeleteTaskCommand(String day, int index) {
        this.day = day;
        this.index = index;
    }

    public void run(TaskList taskList) throws CommandException {
        try {
            taskList.deleteTask(day, index);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(ioobe.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DeleteTaskCommand)) {
            return false;
        }
        DeleteTaskCommand other = (DeleteTaskCommand) o;
        return index == other.index
                && day.equals(other.day);
    }
}
