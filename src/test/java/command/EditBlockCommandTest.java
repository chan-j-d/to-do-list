package command;

import static template.TaskListTemplate.buildTaskListTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.TaskList;
import template.CommandTestTemplate;

public class EditBlockCommandTest extends CommandTestTemplate {

    private static final String BLOCK_NAME_TEST_ONE = "block one";
    private static final String BLOCK_NAME_TEST_TWO = "block two";
    private static final String EDITED_BLOCK_NAME_TEST_ONE = "edited block one";
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

        EditBlockCommand editBlockCommand1 = new EditBlockCommand(BLOCK_NAME_TEST_TWO,
                EDITED_BLOCK_NAME_TEST_TWO);
        editBlockCommand1.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);

        expectedTaskList = buildTaskListTemplate();
        expectedTaskList.addBlock(EDITED_BLOCK_NAME_TEST_TWO);
        expectedTaskList.addBlock(0, EDITED_BLOCK_NAME_TEST_ONE);
        EditBlockCommand editBlockCommand2 = new EditBlockCommand(BLOCK_NAME_TEST_ONE, EDITED_BLOCK_NAME_TEST_ONE);
        editBlockCommand2.run(modelTaskList);
        Assertions.assertEquals(expectedTaskList, modelTaskList);
    }

    @Test
    public void run_invalidBlockName_commandExceptionThrown() {
        EditBlockCommand editBlockCommand1 = new EditBlockCommand("1234", EDITED_BLOCK_NAME_TEST_ONE);
        EditBlockCommand editBlockCommand2 = new EditBlockCommand("frI", EDITED_BLOCK_NAME_TEST_TWO);
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand2.run(modelTaskList));
    }

    @Test
    public void run_editDayOfWeekBlock_commandExceptionThrown() {
        // Edits monday
        EditBlockCommand editBlockCommand1 = new EditBlockCommand("mOndaY", EDITED_BLOCK_NAME_TEST_ONE);
        // Edits friday
        EditBlockCommand editBlockCommand2 = new EditBlockCommand("fRIDAY", EDITED_BLOCK_NAME_TEST_TWO);
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand1.run(modelTaskList));
        Assertions.assertThrows(CommandException.class, () -> editBlockCommand2.run(modelTaskList));
    }
}
