package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;
import template.CommandTestTemplate;

import static template.TaskListTemplate.STRING_DESCRIPTION_ONE;
import static template.TaskListTemplate.STRING_DESCRIPTION_THREE;

public class CompleteTaskCommandTest extends CommandTestTemplate {

    @Test
    public void run_validCompleteCommand_success() {
        CompleteTaskCommand command = new CompleteTaskCommand("monday", 1);
        Task expectedTask = new Task(STRING_DESCRIPTION_ONE);
        expectedTask.markDone();

        command.run(modelTaskList);
        Assertions.assertEquals(expectedTask, modelTaskList.getTask("monday", 1));
    }

    @Test
    public void run_indexAlreadyComplete_noChange() {
        CompleteTaskCommand command = new CompleteTaskCommand("tuesday", 1);
        Task expectedTask = new Task(STRING_DESCRIPTION_THREE);
        expectedTask.markDone();

        command.run(modelTaskList);
        Assertions.assertEquals(expectedTask, modelTaskList.getTask("tuesday", 1));
    }

    @Test
    public void run_invalidIndexCompleteCommand_exceptionThrown() {
        CompleteTaskCommand command1 = new CompleteTaskCommand("monday", -1);
        CompleteTaskCommand command2 = new CompleteTaskCommand("monday", 3);
        CompleteTaskCommand command3 = new CompleteTaskCommand("tuesday", 10);
        CompleteTaskCommand command4 = new CompleteTaskCommand("tuesday", 0);
        CompleteTaskCommand command5 = new CompleteTaskCommand("wednesday", 1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> command1.run(modelTaskList));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> command2.run(modelTaskList));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> command3.run(modelTaskList));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> command4.run(modelTaskList));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> command5.run(modelTaskList));
    }

}