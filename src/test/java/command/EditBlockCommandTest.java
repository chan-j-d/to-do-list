package command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.TaskList;
import template.CommandTestTemplate;

import static template.TaskListTemplate.buildTaskListTemplate;

public class EditBlockCommandTest extends CommandTestTemplate {

    private static final String BLOCK_NAME_TEST_ONE = "block one";
    private static final String BLOCK_NAME_TEST_TWO = "block two";
    private static final String EDITED_BLOCK_NAME_TEST_ONE = "edited block two";
    private static final String EDITED_BLOCK_NAME_TEST_TWO = "edited block two";

    @BeforeEach
    private void addBlockAtStartAndEnd() {
        modelTaskList.addBlock(0, BLOCK_NAME_TEST_ONE);
        modelTaskList.addBlock(BLOCK_NAME_TEST_TWO);
    }

    @Test
    public void run_validEditCommand_success() throws CommandException {
        TaskList expectedTaskList = buildTaskListTemplate();
        expectedTaskList.addBlock(0, BLOCK_NAME_TEST_ONE);
        expectedTaskList.addBlock(EDITED_BLOCK_NAME_TEST_TWO);

        EditBlockCommand editBlockCommand1 = new EditBlockCommand(modelTaskList.size() - 1,
                EDITED_BLOCK_NAME_TEST_TWO);
        editBlockCommand1.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);

        expectedTaskList = buildTaskListTemplate();
        expectedTaskList.addBlock(EDITED_BLOCK_NAME_TEST_TWO);
        expectedTaskList.addBlock(0, EDITED_BLOCK_NAME_TEST_ONE);
        EditBlockCommand editBlockCommand2 = new EditBlockCommand(0, EDITED_BLOCK_NAME_TEST_ONE);
        editBlockCommand2.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);
    }

    @Test
    public void run_indexOutOfBounds_commandExceptionThrown() {
        EditBlockCommand editBlockCommand1 = new EditBlockCommand(modelTaskList.size(), EDITED_BLOCK_NAME_TEST_ONE);
        EditBlockCommand editBlockCommand2 = new EditBlockCommand(-1, EDITED_BLOCK_NAME_TEST_TWO);
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand2.run(modelTaskList));
    }

    @Test
    public void run_editDayOfWeekBlock_commandExceptionThrown() {
        // Edits monday
        EditBlockCommand editBlockCommand1 = new EditBlockCommand(2, EDITED_BLOCK_NAME_TEST_ONE);
        // Edits friday
        EditBlockCommand editBlockCommand2 = new EditBlockCommand(6, EDITED_BLOCK_NAME_TEST_TWO);
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand2.run(modelTaskList));
    }
}
