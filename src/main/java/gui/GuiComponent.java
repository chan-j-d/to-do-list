package gui;

import command.Command;
import io.gui.CommandMessenger;
import javafx.fxml.FXMLLoader;
import task.TaskList;

import java.io.IOException;

public abstract class GuiComponent<T> {

    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private static CommandMessenger COMMAND_MESSENGER;

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

}
