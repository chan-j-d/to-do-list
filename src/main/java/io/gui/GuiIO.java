package io.gui;

import command.Command;
import gui.MainWindow;
import io.IOInterface;
import io.InputException;
import task.TaskList;

public class GuiIO implements IOInterface {

    private final MainWindow mainWindow;
    private final CommandSync commandSync;

    public GuiIO() {
        mainWindow = new MainWindow();
        commandSync = new CommandSync();
    }

    @Override
    public Command<TaskList> getUserInput() throws InputException {
        return commandSync.getUserCommand();
    }

    @Override
    public void updateUser(TaskList taskList) {
        mainWindow.updateWindow(taskList);
    }

    @Override
    public void displayOnStartup(TaskList taskList) {
        mainWindow.updateWindow(taskList);
    }

    @Override
    public void displayErrorMessage(String message) {
        //TODO
    }

    @Override
    public void exit() {
        //TODO
    }
}
