package command;

import task.TaskList;

public class CompleteTaskCommand implements Command<TaskList> {

    private final String day;
    private final int index;

    public CompleteTaskCommand(String day, int index) {
        this.day = day;
        this.index = index;
    }

    public void run(TaskList list) {
        list.completeTask(day, index);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CompleteTaskCommand)) {
            return false;
        }
        CompleteTaskCommand other = (CompleteTaskCommand) o;
        return index == other.index
                && day.equals(other.day);
    }
}
