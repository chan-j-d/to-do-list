package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class GuiComponent {

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    public GuiComponent(String resource, Parent root) {
        fxmlLoader.setLocation(getClass().getResource(resource));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(root);
        try {
            fxmlLoader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
