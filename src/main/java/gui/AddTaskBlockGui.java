package gui;

import command.AddTaskCommand;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class AddTaskBlockGui extends GuiComponent<HBox> {

    private static final String FXML_RESOURCE = "AddTaskBlock.fxml";
    private static boolean HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
    private static TextField EXISTING_DISPLAYED_TEXTFIELD = null;

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
        if (HAS_EXISTING_DISPLAYED_TEXTFIELD) {
            EXISTING_DISPLAYED_TEXTFIELD.setVisible(false);
        }
        textField.setVisible(true);
        EXISTING_DISPLAYED_TEXTFIELD = textField;
        HAS_EXISTING_DISPLAYED_TEXTFIELD = true;
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
        getRoot().getParent().requestFocus();
        textField.setVisible(false);
        HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
        textField.clear();
    }

    private void handleEscKey() {
        getRoot().getParent().requestFocus();
        textField.setVisible(false);
        HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
        textField.clear();
    }

}
