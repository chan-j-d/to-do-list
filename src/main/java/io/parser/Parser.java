package io.parser;

import command.AddTaskCommand;
import command.Command;
import command.CompleteTaskCommand;
import command.DeleteTaskCommand;
import command.EditTaskCommand;
import command.UndoTaskCommand;
import io.IOInterface;
import io.InputException;
import task.BlockNames;
import task.TaskList;
import util.Pair;

import java.util.Arrays;
import java.util.Scanner;

import static command.CommandTypes.COMMAND_ADD;
import static command.CommandTypes.COMMAND_COMPLETE;
import static command.CommandTypes.COMMAND_DELETE;
import static command.CommandTypes.COMMAND_EDIT;
import static command.CommandTypes.COMMAND_UNDO;

public class Parser implements IOInterface {

    private static final String MESSAGE_TOO_FEW_ARGS = "Input has too few arguments.";
    private static final String MESSAGE_INVALID_DAY = "Invalid day argument.";
    private static final String MESSAGE_INVALID_COMMAND_TYPE = "Invalid command type.";
    private static final String MESSAGE_INVALID_NUMBER_FORMAT = "Number argument is not a valid number.";

    private final Scanner scanner;

    public Parser() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
    }

    @Override
    public void displayOnStartup(TaskList taskList) {
        System.out.println(taskList);
    }

    @Override
    public Command<TaskList> getUserInput() throws InputException {
        String userInput = scanner.next();
        try {
            return parse(userInput);
        } catch (ParseException pe) {
            throw new InputException(pe.getMessage());
        }
    }

    @Override
    public void displayErrorMessage(String message) {
        System.err.println(message);
    }

    @Override
    public void updateUser(TaskList taskList) {
        System.out.println(taskList);
    }

    @Override
    public void exit() {
        scanner.close();
    }

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
            case COMMAND_EDIT:
                Pair<Integer, String> editDetailsPair = parseEditDetails(taskDetails);
                return new EditTaskCommand(blockName, editDetailsPair.getFirst(), editDetailsPair.getSecond());
            default:
                throw new ParseException(MESSAGE_INVALID_COMMAND_TYPE);
        }
    }

    private boolean isValidBlockName(String blockName) {
        return BlockNames.isValidBlockName(blockName);
    }

    private int convertToInt(String indexString) throws ParseException {
        try {
            return Integer.parseInt(indexString);
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_FORMAT);
        }
    }

    private Pair<Integer, String> parseEditDetails(String editDetails) {
        String[] details = editDetails.replaceAll("\\s+", " ").split(" ", 2);
        details = Arrays.stream(details)
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

        return new Pair<>(Integer.parseInt(details[0]), details[1]);
    }

}
