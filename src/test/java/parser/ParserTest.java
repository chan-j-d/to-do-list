package parser;

import command.AddTaskCommand;
import command.Command;
import command.CompleteTaskCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.TaskList;

public class ParserTest {

    private static final String STRING_EMPTY = " \r\t  ";
    private static final String STRING_NOT_ENOUGH_ARGS = "do monday   ";
    private static final String STRING_INVALID_COMMAND_TYPE = "abcde monday 12345";
    private static final String STRING_INVALID_BLOCKNAME = "do abcde 12345";
    private static final String STRING_INVALID_INDEX = "do monday abcde";

    private static final String STRING_VALID_DO_COMMAND = "do monday 3";
    private static final Command<TaskList> EXPECTED_COMMAND_VALID_DO = new CompleteTaskCommand("monday", 3);
    private static final String STRING_VALID_ADD_COMMAND = "add sunday abcde fghj asdads123";
    private static final Command<TaskList> EXPECTED_COMMAND_VALID_ADD = new AddTaskCommand("sunday",
            "abcde fghj asdads123");

    private final Parser parser = new Parser();

    @Test
    public void parse_invalidStrings_parseExceptionThrown() {
        // empty string
        Assertions.assertThrows(ParseException.class, () -> parser.parse(STRING_EMPTY));

        // too few arguments
        Assertions.assertThrows(ParseException.class, () -> parser.parse(STRING_NOT_ENOUGH_ARGS));

        // invalid command type
        Assertions.assertThrows(ParseException.class, () -> parser.parse(STRING_INVALID_COMMAND_TYPE));

        // invalid blockname type
        Assertions.assertThrows(ParseException.class, () -> parser.parse(STRING_INVALID_BLOCKNAME));

        // invalid number format
        Assertions.assertThrows(ParseException.class, () -> parser.parse(STRING_INVALID_INDEX));
    }

    @Test
    public void parse_validStrings_success() throws ParseException {
        Command<TaskList> actualCommand = parser.parse(STRING_VALID_DO_COMMAND);
        Assertions.assertEquals(EXPECTED_COMMAND_VALID_DO, actualCommand);

        actualCommand = parser.parse(STRING_VALID_ADD_COMMAND);
        Assertions.assertEquals(EXPECTED_COMMAND_VALID_ADD, actualCommand);
    }

}
