package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import template.CommandTestTemplate;

public class AddTaskCommandTest extends CommandTestTemplate {

    @Test
    public void run_validAddTaskCommand_success() {
        AddTaskCommand command1 = new AddTaskCommand("monday", "some task 1");
        AddTaskCommand command2 = new AddTaskCommand("monday", "some task 2");
        AddTaskCommand command3 = new AddTaskCommand("wednesday", "some task 3");
        int COUNT_EXPECTED_MONDAY_ONE = 3;
        int COUNT_EXPECTED_MONDAY_TWO = 4;
        int COUNT_EXPECTED_WEDNESDAY = 1;

        command1.run(modelTaskList);
        Assertions.assertEquals(COUNT_EXPECTED_MONDAY_ONE, modelTaskList.getNumTasksInBlock("monday"));
        command2.run(modelTaskList);
        Assertions.assertEquals(COUNT_EXPECTED_MONDAY_TWO, modelTaskList.getNumTasksInBlock("monday"));
        command3.run(modelTaskList);
        Assertions.assertEquals(COUNT_EXPECTED_WEDNESDAY, modelTaskList.getNumTasksInBlock("wednesday"));
    }
}