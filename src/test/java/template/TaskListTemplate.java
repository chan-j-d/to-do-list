package template;

import task.TaskList;

public class TaskListTemplate {

    public static TaskList buildTaskListTemplate() {
        TaskList taskList = new TaskList();
        taskList.addTask("monday", "task1");
        taskList.addTask("monday", "task2");

        taskList.addTask("tuesday", "task3");
        taskList.completeTask("tuesday", 1);
        return taskList;
    }
}
