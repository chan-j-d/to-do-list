package gui;

import command.AddTaskCommand;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class AddTaskBlockGui extends GuiComponent<HBox> {

    private static final String FXML_RESOURCE = "AddTaskBlock.fxml";

    @FXML
    private HBox block;
    @FXML
    private TextField textField;

    private final String blockName;

    public AddTaskBlockGui(String blockName) {
        super(FXML_RESOURCE);
        this.blockName = blockName;
    }

    @FXML
    private void createTextField() {
        textField.setVisible(true);
        textField.requestFocus();
    }

    @FXML
    private void respondToKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            handleEnterKey();
        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
            handleEscKey();
        }
        return;
    }

    private void handleEnterKey() {
        AddTaskCommand addTaskCommand = new AddTaskCommand(blockName, textField.getText());
        runUserCommand(addTaskCommand);
        textField.setVisible(false);
        textField.clear();
    }

    private void handleEscKey() {
        textField.setVisible(false);
        textField.clear();
    }

}
