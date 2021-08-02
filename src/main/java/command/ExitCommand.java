package command;

import task.TaskList;

public class ExitCommand implements Command<TaskList> {

    public void run(TaskList list) {
        return;
    }

}
