package parser;

import command.*;
import task.BlockNames;
import task.TaskList;
import util.Pair;

import java.util.Arrays;

import static command.CommandTypes.*;

public class Parser {

    private static final String MESSAGE_TOO_FEW_ARGS = "Input has too few arguments.";
    private static final String MESSAGE_INVALID_DAY = "Invalid day argument.";
    private static final String MESSAGE_INVALID_COMMAND_TYPE = "Invalid command type.";

    public Command<TaskList> parse(String input) throws ParseException {
        String[] details = input.replaceAll("\\s+", " ").split(" ", 3);
        details = Arrays.stream(details)
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

        if (details.length != 3) {
            throw new ParseException(MESSAGE_TOO_FEW_ARGS);
        }

        String commandType = details[0].toLowerCase().strip();
        String blockName = details[1].toLowerCase().strip();
        String taskDetails = details[2];

        if (!isValidBlockName(blockName)) {
            throw new ParseException(MESSAGE_INVALID_DAY);
        }

        switch (commandType) {
            case COMMAND_ADD:
                return new AddTaskCommand(blockName, taskDetails);
            case COMMAND_COMPLETE:
                return new CompleteTaskCommand(blockName, convertToInt(taskDetails));
            case COMMAND_UNDO:
                return new UndoTaskCommand(blockName, convertToInt(taskDetails));
            case COMMAND_DELETE:
                return new DeleteTaskCommand(blockName, convertToInt(taskDetails));
            default:
                throw new ParseException(MESSAGE_INVALID_COMMAND_TYPE);
        }
    }

    private boolean isValidBlockName(String blockName) {
        return BlockNames.isValidBlockName(blockName);
    }

    private int convertToInt(String indexString) {
        return Integer.parseInt(indexString);
    }

}
