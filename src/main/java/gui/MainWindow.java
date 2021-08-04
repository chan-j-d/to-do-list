package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "MainWindow.fxml";

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ScrollPane scrollPane;

    public MainWindow() {
        super(FXML_RESOURCE);
    }

}
