package command;

import static template.TaskListTemplate.buildTaskListTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.TaskBlock;
import task.TaskList;
import template.CommandTestTemplate;

public class AddBlockCommandTest extends CommandTestTemplate {

    private static final String BLOCK_NAME_TEST_ONE = "block one";
    private static final String BLOCK_NAME_TEST_TWO = "block two";
    private static final String BLOCK_NAME_INVALID_NORMAL = "monday";
    private static final String BLOCK_NAME_INVALID_WITH_UPPERCASE = "tUeSdAy";
    private static final int INDEX_TEST = 2;

    @Test
    public void run_validAddBlockCommand_success() throws CommandException {
        AddBlockCommand addBlockCommand1 = new AddBlockCommand(BLOCK_NAME_TEST_ONE);
        TaskList expectedList1 = buildTaskListTemplate();
        expectedList1.addBlock(BLOCK_NAME_TEST_ONE);

        addBlockCommand1.run(modelTaskList);
        Assertions.assertEquals(expectedList1, modelTaskList);
        TaskBlock expectedBlock1 = new TaskBlock(BLOCK_NAME_TEST_ONE);
        Assertions.assertEquals(modelTaskList.getBlock(modelTaskList.size() - 1), expectedBlock1);

        reset();
        AddBlockCommand addBlockCommand2 = new AddBlockCommand(INDEX_TEST, BLOCK_NAME_TEST_TWO);
        TaskList expectedList2 = buildTaskListTemplate();
        expectedList2.addBlock(2, BLOCK_NAME_TEST_TWO);

        addBlockCommand2.run(modelTaskList);
        Assertions.assertEquals(expectedList2, modelTaskList);
        TaskBlock expectedBlock2 = new TaskBlock(BLOCK_NAME_TEST_TWO);
        Assertions.assertEquals(modelTaskList.getBlock(INDEX_TEST), expectedBlock2);
    }

    @Test
    public void run_invalidBlockName_commandExceptionThrown() {
        AddBlockCommand invalidAddBlockCommand1 = new AddBlockCommand(BLOCK_NAME_INVALID_NORMAL);
        AddBlockCommand invalidAddBlockCommand2 = new AddBlockCommand(BLOCK_NAME_INVALID_WITH_UPPERCASE);

        Assertions.assertThrows(CommandException.class, () -> invalidAddBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> invalidAddBlockCommand2.run(modelTaskList));
    }

}
