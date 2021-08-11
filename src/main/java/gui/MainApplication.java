package gui;

import command.ExitCommand;
import core.ToDoList;
import io.IoInterface;
import io.gui.GuiIO;
import java.util.concurrent.CompletableFuture;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
    }

    @Override
    public void stop() {
        mainWindow.runUserCommand(new ExitCommand());
        toDoListRunner.join();
    }

}
