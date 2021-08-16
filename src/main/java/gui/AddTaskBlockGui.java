package gui;

import command.AddBlockCommand;
import command.AddTaskCommand;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class AddTaskBlockGui extends GuiComponent<HBox> {

    private static final String FXML_RESOURCE = "AddTaskBlockGui.fxml";
    private static final String TEXT_FIELD_DEFAULT_MESSAGE = "Header of new block";


    private static boolean HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
    private static TextField EXISTING_DISPLAYED_TEXTFIELD = null;

    @FXML
    private HBox block;
    @FXML
    private TextField textField;

    private final int index;

    public AddTaskBlockGui(int index) {
        super(FXML_RESOURCE);
        this.index = index;
    }

    @FXML
    private void createTextField() {
        if (HAS_EXISTING_DISPLAYED_TEXTFIELD) {
            EXISTING_DISPLAYED_TEXTFIELD.setVisible(false);
        }
        textField.setText(TEXT_FIELD_DEFAULT_MESSAGE);
        textField.positionCaret(TEXT_FIELD_DEFAULT_MESSAGE.length());
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
        AddBlockCommand addBlockCommand = new AddBlockCommand(index, textField.getText());
        runUserCommand(addBlockCommand);
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
