package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;
import template.CommandTestTemplate;

import static template.TaskListTemplate.STRING_DESCRIPTION_ONE;
import static template.TaskListTemplate.STRING_DESCRIPTION_THREE;

public class EditTaskCommandTest extends CommandTestTemplate {

    private final String EDIT_DESCRIPTION_ONE = "edit to this one";
    private final String EDIT_DESCRIPTION_TWO = "edit to this two";

    @Test
    public void run_validEditCommand_success() throws CommandException {
        EditTaskCommand command1 = new EditTaskCommand("monday", 2, EDIT_DESCRIPTION_ONE);
        EditTaskCommand command2 = new EditTaskCommand("tuesday", 1, EDIT_DESCRIPTION_TWO);
        Task expectedTask1 = new Task(EDIT_DESCRIPTION_ONE);
        Task expectedTask2 = new Task(EDIT_DESCRIPTION_TWO);
        expectedTask2.markDone();

        command1.run(modelTaskList);
        Assertions.assertEquals(expectedTask1, modelTaskList.getTask("monday", 2));
        command2.run(modelTaskList);
        Assertions.assertEquals(expectedTask2, modelTaskList.getTask("tuesday", 1));
    }


    @Test
    public void run_invalidIndexEditCommand_exceptionThrown() {
        EditTaskCommand command1 = new EditTaskCommand("monday", -1, EDIT_DESCRIPTION_ONE);
        EditTaskCommand command2 = new EditTaskCommand("monday", 3, EDIT_DESCRIPTION_ONE);
        EditTaskCommand command3 = new EditTaskCommand("tuesday", 10, EDIT_DESCRIPTION_ONE);
        EditTaskCommand command4 = new EditTaskCommand("tuesday", 0, EDIT_DESCRIPTION_ONE);
        EditTaskCommand command5 = new EditTaskCommand("wednesday", 1, EDIT_DESCRIPTION_TWO);

        Assertions.assertThrows(CommandException.class, () -> command1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command2.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command3.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command4.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command5.run(modelTaskList));
    }

}
