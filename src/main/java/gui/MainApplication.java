package gui;

import core.ToDoList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

     @Override
     public void start(Stage stage) {
         ToDoList toDoList = new ToDoList();
         MainWindow mainWindow = new MainWindow(toDoList.getTaskList());
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
