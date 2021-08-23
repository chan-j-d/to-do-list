package gui;

import command.ExitCommand;
import core.ToDoList;
import io.IoInterface;
import io.gui.GuiIO;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logging.LogsManager;
import settings.WindowSettings;
import storage.JsonSettingsStorageImpl;
import storage.Storage;
import task.Task;
import util.DateUtil;


public class MainApplication extends Application {

    private static final String WINDOW_SETTING_FILENAME = "window-settings";
    private static final String MESSAGE_NO_SETTINGS_FOUND = "No settings were found. Using default.";
    private static final String MESSAGE_SETTINGS_NOT_SAVED = "Settings were not saved properly.";
    private static final double DEFAULT_HEIGHT = 400.0;
    private static final double DEFAULT_WIDTH = 400.0;
    private static final double MIN_HEIGHT = 400.0;
    private static final double MIN_WIDTH = 400.0;
    private static final Logger LOGGER = LogsManager.getLogger(MainApplication.class);

    private MainWindow mainWindow;
    private CompletableFuture<Void> toDoListRunner;
    private Path saveFileDirectory;
    private Path windowsSettingPath;
    private Storage<WindowSettings> settingsStorage;
    private Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        // get save directory
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();
        saveFileDirectory = Paths.get(args.get(0));

        // create ToDoList instance and start running
        mainWindow = new MainWindow();
        IoInterface ioInterface = new GuiIO(mainWindow);
        ToDoList toDoList = new ToDoList(ioInterface, saveFileDirectory);
        toDoListRunner = CompletableFuture.runAsync(toDoList::run);

        // create window
        settingsStorage = new JsonSettingsStorageImpl();
        windowsSettingPath = saveFileDirectory.resolve(WINDOW_SETTING_FILENAME);

        WindowSettings settings = getDefaultWindowSettings();
        try {
            settings = settingsStorage.load(windowsSettingPath);
        } catch (IOException ioe) {
            LOGGER.log(Level.INFO, MESSAGE_NO_SETTINGS_FOUND, ioe);
        }
        double height = settings.getPrefHeight();
        double width = settings.getPrefWidth();

        Scene scene = new Scene(mainWindow.getRoot());
        stage.setScene(scene);
        stage.setTitle("To-Do-List");
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.show();

        runStartupRoutine(toDoList);
    }

    @Override
    public void stop() {
        saveWindowSettings();
        mainWindow.runUserCommand(new ExitCommand());
        toDoListRunner.join();
    }

    /**
     * Run any necessary startup features.
     */
    public void runStartupRoutine(ToDoList toDoList) {
        runPushTasksFeature(toDoList);
    }

    /**
     * Runs a suggestion to push incomplete tasks and delete completed tasks from previous day.
     */
    private void runPushTasksFeature(ToDoList toDoList) {
        String previousDay = DateUtil.getRealPreviousDay();
        String currentDay = DateUtil.getRealDay();
        List<Task> tasks = toDoList.getTaskList().getTasksInBlock(DateUtil.getRealPreviousDay());
        if (tasks.size() == 0) {
            return;
        }
        PushTasksWindow pushTasksWindow = new PushTasksWindow(tasks, previousDay, currentDay);
        Scene scene = new Scene(pushTasksWindow.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinHeight(100.0);
        stage.setMinWidth(100.0);
        stage.setTitle("Yesterday's tasks");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static WindowSettings getDefaultWindowSettings() {
        return new WindowSettings(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    private void saveWindowSettings() {
        double height = mainStage.getHeight();
        double width = mainStage.getWidth();
        try {
            settingsStorage.save(new WindowSettings(height, width), windowsSettingPath);
        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, MESSAGE_SETTINGS_NOT_SAVED, ioe);
        }
    }
}
