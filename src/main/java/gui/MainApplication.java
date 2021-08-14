package gui;

import command.ExitCommand;
import core.ToDoList;
import io.IoInterface;
import io.gui.GuiIO;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javafx.application.Application;
import javafx.scene.Scene;
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

    public void runStartupRoutine(ToDoList toDoList) {
        runPushTasksFeature(toDoList);
    }

    public void runPushTasksFeature(ToDoList toDoList) {
        List<Task> tasks = toDoList.getTaskList().getTasksInBlock(DateUtil.getRealPreviousDay());
        PushTasksWindow pushTasksWindow = new PushTasksWindow(tasks);
        Scene scene = new Scene(pushTasksWindow.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
