package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import task.TaskList;

public class MainApplication extends Application {

    private final TaskList taskList;

    public MainApplication(TaskList taskList) {
        this.taskList = taskList;
    }

     @Override
     public void start(Stage stage) {
         MainWindow mainWindow = new MainWindow(taskList);
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
