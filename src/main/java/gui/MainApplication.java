package gui;

import command.ExitCommand;
import core.ToDoList;
import io.IoInterface;
import io.gui.GuiIO;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import task.Task;
import util.DateUtil;


public class MainApplication extends Application {

    private MainWindow mainWindow;
    private CompletableFuture<Void> toDoListRunner;

    @Override
    public void start(Stage stage) {
        mainWindow = new MainWindow();
        IoInterface ioInterface = new GuiIO(mainWindow);
        ToDoList toDoList = new ToDoList(ioInterface);
        toDoListRunner = CompletableFuture.runAsync(toDoList::run);
        Scene scene = new Scene(mainWindow.getRoot());
        stage.setScene(scene);
        stage.setTitle("To-Do-List");
        stage.setMinHeight(400.0);
        stage.setMinWidth(400.0);
        stage.show();

        runStartupRoutine(toDoList);
    }

    @Override
    public void stop() {
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
    public void runPushTasksFeature(ToDoList toDoList) {
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
}
