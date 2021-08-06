package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddTaskBlockGui extends GuiComponent<HBox> {

    private static final String FXML_RESOURCE = "AddTaskBlock.fxml";

    @FXML
    private HBox block;

    private final TextField textField;

    public AddTaskBlockGui() {
        super(FXML_RESOURCE);
        textField = new TextField();
    }

    @FXML
    private void createTextField() {
        block.getChildren().add(textField);
    }

}
