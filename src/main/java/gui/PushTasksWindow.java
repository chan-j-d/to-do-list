package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class PushTasksWindow extends GuiComponent<Scene> {

    private static final String FXML_RESOURCE = "PushTasksWindow.fxml";

    @FXML
    private VBox prevDayTasksHolder;

    public PushTasksWindow() {
        super(FXML_RESOURCE);
    }
}
