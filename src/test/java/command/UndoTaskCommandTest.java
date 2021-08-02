package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;
import template.CommandTestTemplate;

import static template.TaskListTemplate.STRING_DESCRIPTION_ONE;
import static template.TaskListTemplate.STRING_DESCRIPTION_THREE;

public class UndoTaskCommandTest extends CommandTestTemplate {

    @Test
    public void run_validUndoCommand_success() throws CommandException {
        UndoTaskCommand command = new UndoTaskCommand("tuesday", 1);
        Task expectedTask = new Task(STRING_DESCRIPTION_THREE);
        expectedTask.markUndone();

        command.run(modelTaskList);
        Assertions.assertEquals(expectedTask, modelTaskList.getTask("tuesday", 1));
    }

    @Test
    public void run_indexAlreadyUndone_noChange() throws CommandException {
        UndoTaskCommand command = new UndoTaskCommand("monday", 1);
        Task expectedTask = new Task(STRING_DESCRIPTION_ONE);

        command.run(modelTaskList);
        Assertions.assertEquals(expectedTask, modelTaskList.getTask("monday", 1));
    }

    @Test
    public void run_invalidIndexUndoCommand_exceptionThrown() {
        UndoTaskCommand command1 = new UndoTaskCommand("monday", -1);
        UndoTaskCommand command2 = new UndoTaskCommand("monday", 3);
        UndoTaskCommand command3 = new UndoTaskCommand("tuesday", 10);
        UndoTaskCommand command4 = new UndoTaskCommand("tuesday", 0);
        UndoTaskCommand command5 = new UndoTaskCommand("wednesday", 1);

        Assertions.assertThrows(CommandException.class, () -> command1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command2.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command3.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command4.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command5.run(modelTaskList));
    }

}
