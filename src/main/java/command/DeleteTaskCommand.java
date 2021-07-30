package command;

import task.TaskList;

public class DeleteTaskCommand implements Command<TaskList> {

    private final int index;
    private final String day;

    public DeleteTaskCommand(String day, int index) {
        this.day = day;
        this.index = index;
    }

    public void run(TaskList taskList) {
        taskList.deleteTask(day, index);
    }

}
