package template;

import task.TaskList;

public class TaskListTemplate {

    public static final String STRING_DESCRIPTION_ONE = "task1";
    public static final String STRING_DESCRIPTION_TWO = "task2";
    public static final String STRING_DESCRIPTION_THREE = "task3";

    /**
     * Creates a standard {@code TaskList} to be used as a template for tests.
     */
    public static TaskList buildTaskListTemplate() {
        TaskList taskList = new TaskList();
        taskList.addTask("monday", STRING_DESCRIPTION_ONE);
        taskList.addTask("monday", STRING_DESCRIPTION_TWO);

        taskList.addTask("tuesday", STRING_DESCRIPTION_THREE);
        taskList.completeTask("tuesday", 1);
        return taskList;
    }
}
