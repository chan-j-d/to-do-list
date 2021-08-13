package command;

import static template.TaskListTemplate.STRING_DESCRIPTION_ONE;
import static template.TaskListTemplate.STRING_DESCRIPTION_THREE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;
import template.CommandTestTemplate;

public class MoveTaskCommandTest extends CommandTestTemplate {

    @Test
    public void run_validMoveTaskCommand_success() throws CommandException {
        MoveTaskCommand command1 = new MoveTaskCommand("monday", 1, "wednesday");
        Task expectedTask1 = new Task(STRING_DESCRIPTION_ONE);

        command1.run(modelTaskList);
        Assertions.assertNotEquals(expectedTask1, modelTaskList.getTask("monday", 1));
        Assertions.assertEquals(expectedTask1, modelTaskList.getTask("wednesday", 1));

        MoveTaskCommand command2 = new MoveTaskCommand("tuesday", 1, "wednesday");
        Task expectedTask2 = new Task(STRING_DESCRIPTION_THREE);
        expectedTask2.markDone();

        command2.run(modelTaskList);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> modelTaskList.getTask("tuesday", 1));
        Assertions.assertEquals(expectedTask2, modelTaskList.getTask("wednesday", 2));
    }

    @Test
    public void run_invalidIndexMoveTaskCommand_exceptionThrown() {
        MoveTaskCommand command1 = new MoveTaskCommand("monday", -1, "sunday");
        MoveTaskCommand command2 = new MoveTaskCommand("monday", 3, "saturday");
        MoveTaskCommand command3 = new MoveTaskCommand("tuesday", 10, "sunday");
        MoveTaskCommand command4 = new MoveTaskCommand("tuesday", 0, "saturday");
        MoveTaskCommand command5 = new MoveTaskCommand("wednesday", 1, "sunday");

        Assertions.assertThrows(CommandException.class, () -> command1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command2.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command3.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command4.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command5.run(modelTaskList));
    }



}
