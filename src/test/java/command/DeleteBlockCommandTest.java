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

        DeleteBlockCommand deleteBlockCommand1 = new DeleteBlockCommand(modelTaskList.size() - 1);
        deleteBlockCommand1.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);

        expectedTaskList.deleteBlock(0);
        DeleteBlockCommand deleteBlockCommand2 = new DeleteBlockCommand(0);
        deleteBlockCommand2.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);
    }

    @Test
    public void run_indexOutOfBounds_commandExceptionThrown() {
        DeleteBlockCommand deleteBlockCommand1 = new DeleteBlockCommand(modelTaskList.size());
        DeleteBlockCommand deleteBlockCommand2 = new DeleteBlockCommand(-1);
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand2.run(modelTaskList));
    }

    @Test
    public void run_deleteDayOfWeekBlock_commandExceptionThrown() {
        DeleteBlockCommand deleteBlockCommand1 = new DeleteBlockCommand(2); // Deletes Monday
        DeleteBlockCommand deleteBlockCommand2 = new DeleteBlockCommand(6); // Deletes Friday
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> deleteBlockCommand2.run(modelTaskList));
    }
}
