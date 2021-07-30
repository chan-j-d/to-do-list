package command;

import task.TaskList;

public class EmptyCommand implements Command<TaskList> {

    public void run(TaskList list) {
        return;
    }

}
