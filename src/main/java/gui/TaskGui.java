package gui;

import command.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import task.Task;

public class TaskGui extends GuiComponent<BorderPane> {

    private static final String FXML_RESOURCE = "TaskGui.fxml";
    private static boolean HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
    private static TaskGui CURRENTLY_EDITING_TASK = null;

    @FXML
    private BorderPane box;
    @FXML
    private Label taskDescriptionLabel;
    @FXML
    private ToggleButton doneButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField editField;

    private final String taskBlockName;
    private String currentDescription;
    private final int index;

    public TaskGui(String taskBlockName, int index, Task task) {
        super(FXML_RESOURCE);
        this.taskBlockName = taskBlockName;
        this.index = index;
        currentDescription = task.getDescription();
        taskDescriptionLabel.setText(currentDescription);
        doneButton.setSelected(task.isDone());
    }

    @FXML
    private void registerToggle() {
        if (!doneButton.isSelected()) {
            runUserCommand(new CompleteTaskCommand(taskBlockName, index));
        } else {
            runUserCommand(new UndoTaskCommand(taskBlockName, index));
        }
    }

    @FXML
    private void registerDelete() {
        runUserCommand(new DeleteTaskCommand(taskBlockName, index));
    }

    @FXML
    private void registerEditSignal() {
        if (HAS_EXISTING_DISPLAYED_TEXTFIELD) {
            CURRENTLY_EDITING_TASK.switchToLabel();
        }
        HAS_EXISTING_DISPLAYED_TEXTFIELD = true;
        CURRENTLY_EDITING_TASK = this;
        editField.setText(currentDescription);
        switchToTextField();
        Platform.runLater(() -> {
            editField.requestFocus();
            editField.positionCaret(currentDescription.length());
            editField.selectAll();
        });
    }

    @FXML
    private void registerEdit(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            handleEnterKey();
        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
            handleEscKey();
        }
    }

    private void handleEnterKey() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(taskBlockName, index, editField.getText());
        runUserCommand(editTaskCommand);
        removeFocus();
        switchToLabel();
        HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
        editField.clear();
    }

    private void handleEscKey() {
        removeFocus();
        switchToLabel();
        HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
        editField.clear();
    }

    private void removeFocus() {
        getRoot().getParent().requestFocus();
    }

    private void switchToTextField() {
        taskDescriptionLabel.setVisible(false);
        editField.setVisible(true);
    }

    private void switchToLabel() {
        editField.setVisible(false);
        taskDescriptionLabel.setVisible(true);
    }

}
