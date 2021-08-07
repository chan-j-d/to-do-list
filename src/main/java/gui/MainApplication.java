package gui;

import core.ToDoList;
import io.IOInterface;
import io.gui.GuiIO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class MainApplication extends Application {

     @Override
     public void start(Stage stage) {
         MainWindow mainWindow = new MainWindow();
         IOInterface ioInterface = new GuiIO(mainWindow);
         ToDoList toDoList = new ToDoList(ioInterface);
         CompletableFuture<Void> future = CompletableFuture.runAsync(() -> toDoList.run());
         Scene scene = new Scene(mainWindow.getRoot());
         stage.setScene(scene);
         stage.setMinHeight(400.0);
         stage.setMinWidth(400.0);
         stage.show();
     }

     public static void main(String[] args) {
         Application.launch(MainApplication.class, args);
     }

}
