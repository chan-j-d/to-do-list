package command;

import java.util.List;

public class CommandTypes {

    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_COMPLETE = "do";
    public static final String COMMAND_UNDO = "undo";

    public static List<String> COMMAND_TYPES = List.of(COMMAND_ADD, COMMAND_COMPLETE, COMMAND_DELETE, COMMAND_UNDO);

}
