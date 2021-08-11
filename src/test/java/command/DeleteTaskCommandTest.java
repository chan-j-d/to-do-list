package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import template.CommandTestTemplate;

public class DeleteTaskCommandTest extends CommandTestTemplate {

    @Test
    public void run_validDeleteCommand_success() throws CommandException {
        int expectedNumberOnMondayFirst = 1;
        DeleteTaskCommand command1 = new DeleteTaskCommand("monday", 2);
        command1.run(modelTaskList);
        Assertions.assertEquals(expectedNumberOnMondayFirst,
                modelTaskList.getNumTasksInBlock("monday"));

        int expectedNumberOnTuesday = 0;
        DeleteTaskCommand command2 = new DeleteTaskCommand("tuesday", 1);
        command2.run(modelTaskList);
        Assertions.assertEquals(expectedNumberOnTuesday,
                modelTaskList.getNumTasksInBlock("tuesday"));
        // ensure monday has not changed
        Assertions.assertEquals(expectedNumberOnMondayFirst,
                modelTaskList.getNumTasksInBlock("monday"));

        int expectedNumberOnMondaySecond = 0;
        DeleteTaskCommand command3 = new DeleteTaskCommand("monday", 1);
        command3.run(modelTaskList);
        Assertions.assertEquals(expectedNumberOnMondaySecond,
                modelTaskList.getNumTasksInBlock("monday"));
    }

    @Test
    public void run_invalidIndexDeleteCommand_exceptionThrown() {
        DeleteTaskCommand command1 = new DeleteTaskCommand("monday", -1);
        DeleteTaskCommand command2 = new DeleteTaskCommand("monday", 3);
        DeleteTaskCommand command3 = new DeleteTaskCommand("tuesday", 10);
        DeleteTaskCommand command4 = new DeleteTaskCommand("tuesday", 0);
        DeleteTaskCommand command5 = new DeleteTaskCommand("wednesday", 1);

        Assertions.assertThrows(CommandException.class, () -> command1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command2.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command3.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command4.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> command5.run(modelTaskList));
    }

}
