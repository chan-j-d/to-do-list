package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

     @Override
     public void start(Stage stage) {
         MainWindow root = new MainWindow(stage);
         stage.setMinHeight(400.0);
         stage.setMinWidth(400.0);
         stage.show();
     }

     public static void main(String[] args) {
         Application.launch(MainApplication.class, args);
     }

}
