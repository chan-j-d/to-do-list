package command;

import static template.TaskListTemplate.buildTaskListTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.TaskList;
import template.CommandTestTemplate;

public class DeleteBlockCommandTest extends CommandTestTemplate {

    private static final String BLOCK_NAME_TEST_ONE = "block one";
    private static final String BLOCK_NAME_TEST_TWO = "block two";

    @BeforeEach
    private void addBlockAtStartAndEnd() {
        modelTaskList.addBlock(0, BLOCK_NAME_TEST_ONE);
        modelTaskList.addBlock(BLOCK_NAME_TEST_TWO);
    }

    @Test
    public void run_validDeleteCommand_success() throws CommandException {
        TaskList expectedTaskList = buildTaskListTemplate();
        expectedTaskList.addBlock(0, BLOCK_NAME_TEST_ONE);

        DeleteBlockCommand deleteBlockCommand1 = new DeleteBlockCommand(BLOCK_NAME_TEST_TWO);
        deleteBlockCommand1.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);

        expectedTaskList.deleteBlock(BLOCK_NAME_TEST_ONE);
        DeleteBlockCommand deleteBlockCommand2 = new DeleteBlockCommand(BLOCK_NAME_TEST_ONE);
        deleteBlockCommand2.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);
    }

    @Test
    public void run_missingBlockNameToDelete_commandExceptionThrown() {
        DeleteBlockCommand deleteBlockCommand1 = new DeleteBlockCommand("12345");
        DeleteBlockCommand deleteBlockCommand2 = new DeleteBlockCommand("fRi"); // Deletes Frida
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand2.run(modelTaskList));
    }

    @Test
    public void run_deleteDayOfWeekBlock_commandExceptionThrown() {
        DeleteBlockCommand deleteBlockCommand1 = new DeleteBlockCommand("monDaY"); // Deletes Monday
        DeleteBlockCommand deleteBlockCommand2 = new DeleteBlockCommand("fRiDaY"); // Deletes Friday
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand2.run(modelTaskList));
    }
}
