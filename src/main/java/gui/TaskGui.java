package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TaskGui extends GuiComponent<HBox> {

    private static final String FXML_RESOURCE = "TaskGui.fxml";

    @FXML
    private BorderPane box;
    @FXML
    private Label taskDescriptionLabel;
    @FXML
    private ToggleButton doneButton;
    @FXML
    private Button deleteButton;

    public TaskGui(String taskDescription) {
        super(FXML_RESOURCE);
        taskDescriptionLabel.setText(taskDescription);
    }


}
