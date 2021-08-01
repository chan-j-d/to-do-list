package template;

import org.junit.jupiter.api.BeforeEach;
import task.TaskList;

public class CommandTestTemplate {

    protected TaskList modelTaskList;

    @BeforeEach
    public void createNewTemplate() {
        modelTaskList = TaskListTemplate.buildTaskListTemplate();
    }

}
