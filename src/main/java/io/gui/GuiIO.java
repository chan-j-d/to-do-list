package io.gui;

import command.Command;
import gui.MainWindow;
import io.InputException;
import io.IoInterface;
import java.util.List;
import task.TaskList;

public class GuiIO implements IoInterface {

    private final MainWindow mainWindow;
    private final CommandSync commandSync;

    /**
     * Creates a new IoInterface for the given {@code mainWindow} to communicate with {@code ToDoList}.
     */
    public GuiIO(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        commandSync = new CommandSync();
        mainWindow.setCommandMessenger(commandSync);
    }

    @Override
    public List<Command<TaskList>> getUserInput() throws InputException {
        return commandSync.getUserCommand();
    }

    @Override
    public void updateUserBlock(TaskList taskList, int index) {
        mainWindow.updateUserBlock(taskList, index);
    }

    @Override
    public void removeBlock(TaskList taskList, int index) {
        mainWindow.removeBlock(taskList, index);
    }

    @Override
    public void addBlock(TaskList taskList, int index) {
        mainWindow.addBlock(taskList, index);
    }

    @Override
    public void displayOnStartup(TaskList taskList) {
        mainWindow.updateWindow(taskList);
    }

    @Override
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
        //TODO
    }

    @Override
    public void exit() {
        //TODO
    }
}
