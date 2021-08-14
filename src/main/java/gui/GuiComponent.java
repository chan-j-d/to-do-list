package gui;

import command.Command;
import io.gui.CommandMessenger;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import task.TaskList;

public abstract class GuiComponent<T> {

    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private static CommandMessenger COMMAND_MESSENGER;

    /**
     * Creates a new gui component loaded from an {@code resource}.
     */
    public GuiComponent(String resource) {
        fxmlLoader.setLocation(getClass().getResource(resource));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public T getRoot() {
        return fxmlLoader.getRoot();
    }

    public void setCommandMessenger(CommandMessenger commandMessenger) {
        COMMAND_MESSENGER = commandMessenger;
    }

    protected void runUserCommand(Command<TaskList> command) {
        COMMAND_MESSENGER.updateUserCommand(command);
    }

    protected void runUserCommands(List<Command<TaskList>> commands) {
        COMMAND_MESSENGER.updateUserCommands(commands);
    }

}
