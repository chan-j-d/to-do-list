package parser;

import util.Pair;

import java.util.Arrays;

import static task.TaskList.DAYS;

public class Parser {

    private static final String MESSAGE_TOO_FEW_ARGS = "Input has too few arguments.";
    private static final String MESSAGE_INVALID_DAY = "Invalid day argument.";

    public Pair<String, String> parse(String input) throws ParseException {
        String[] details = input.replaceAll("\\s+", " ").split(" ", 2);
        details = Arrays.stream(details)
                .filter(x -> !x.isEmpty())
                .toArray(String[]::new);

        if (details.length != 2) {
            throw new ParseException(MESSAGE_TOO_FEW_ARGS);
        }

        if (!DAYS.contains(details[0].toLowerCase())) {
            throw new ParseException(MESSAGE_INVALID_DAY);
        }

        String day = details[0].toLowerCase();
        String taskString = details[1];
        return new Pair<>(day, taskString);
    }

}
