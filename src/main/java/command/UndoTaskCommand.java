package command;

import task.TaskList;

public class UndoTaskCommand implements Command<TaskList> {

    private final String day;
    private final int index;

    public UndoTaskCommand(String day, int index) {
        this.day = day;
        this.index = index;
    }

    public void run(TaskList taskList) {
        taskList.uncompleteTask(day, index);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UndoTaskCommand)) {
            return false;
        }
        UndoTaskCommand other = (UndoTaskCommand) o;
        return index == other.index
                && day.equals(other.day);
    }
}
