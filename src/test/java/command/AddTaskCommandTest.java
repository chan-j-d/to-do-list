package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import template.CommandTestTemplate;

public class AddTaskCommandTest extends CommandTestTemplate {

    @Test
    public void run_validAddTaskCommand_success() {
        int expectedCount1 = 3;
        AddTaskCommand command1 = new AddTaskCommand("monday", "some task 1");
        command1.run(modelTaskList);
        Assertions.assertEquals(expectedCount1, modelTaskList.getNumTasksInBlock("monday"));

        int expectedCount2 = 4;
        AddTaskCommand command2 = new AddTaskCommand("monday", "some task 2");
        command2.run(modelTaskList);
        Assertions.assertEquals(expectedCount2, modelTaskList.getNumTasksInBlock("monday"));

        int expectedCount3 = 1;
        AddTaskCommand command3 = new AddTaskCommand("wednesday", "some task 3");
        command3.run(modelTaskList);
        Assertions.assertEquals(expectedCount3, modelTaskList.getNumTasksInBlock("wednesday"));
    }
}
