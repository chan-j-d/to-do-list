package gui;

import static task.TaskBlock.STARTING_COUNT;

import command.DeleteBlockCommand;
import command.EditBlockCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskBlock;

import java.util.List;

public class DeletableTaskBlockGui extends TaskContainingBlock {

    private static final String FXML_RESOURCE = "DeletableTaskBlockGui.fxml";
    private static boolean HAS_EXISTING_DISPLAYED_TEXTFIELD = false;
    private static DeletableTaskBlockGui CURRENTLY_EDITING_BLOCK_HEADER;

    @FXML
    private VBox block;
    @FXML
    private Label label;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField editField;
    @FXML
    private Region line;

    private final TaskBlock taskBlock;
    private final String blockHeader;
    private final AddTaskGui addTaskGui;

    /**
     * Creates a new deletable {@code TaskBlockGui} with the given header.
     * It is also required to know its own {@code index} (starting at 0).
     */
    public DeletableTaskBlockGui(TaskBlock taskBlock) {
        super(FXML_RESOURCE);
        this.taskBlock = taskBlock;
        this.blockHeader = taskBlock.getBlockName();
        this.addTaskGui = new AddTaskGui(blockHeader);
        init();
    }

    /**
     * Initialises the individual tasks within the task block.
     * To note, these individual tasks do not have the push button.
     */
    public void init() {
        label.setText(blockHeader);
        int index = STARTING_COUNT;
        for (Task task : taskBlock.getTasks()) {
            TaskGui newTaskGui = new TaskGui(blockHeader, index++, task);
            newTaskGui.removePushButton();
            block.getChildren().add(newTaskGui.getRoot());
        }
        block.getChildren().add(addTaskGui.getRoot());
    }

    @FXML
    private void registerEditSignal() {
        if (HAS_EXISTING_DISPLAYED_TEXTFIELD) {
            CURRENTLY_EDITING_BLOCK_HEADER.switchToLabel();
        }
        HAS_EXISTING_DISPLAYED_TEXTFIELD = true;
        CURRENTLY_EDITING_BLOCK_HEADER = this;
        editField.setText(blockHeader);
        switchToTextField();
        Platform.runLater(() -> {
            editField.requestFocus();
            editField.positionCaret(blockHeader.length());
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
        EditBlockCommand editBlockCommand = new EditBlockCommand(blockHeader, editField.getText());
        runUserCommand(editBlockCommand);
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
        label.setVisible(false);
        editField.setVisible(true);
    }

    private void switchToLabel() {
        editField.setVisible(false);
        label.setVisible(true);
    }

    @FXML
    private void registerDelete() {
        DeleteBlockCommand deleteBlockCommand = new DeleteBlockCommand(blockHeader);
        runUserCommand(deleteBlockCommand);
    }

    @Override
    public void replaceExistingTasks(List<Task> tasks) {
        int index = STARTING_COUNT;
        block.getChildren().clear();
        block.getChildren().addAll(label, line);
        for (Task task : taskBlock.getTasks()) {
            TaskGui newTaskGui = new TaskGui(blockHeader, index++, task);
            newTaskGui.removePushButton();
            block.getChildren().add(newTaskGui.getRoot());
        }
        block.getChildren().add(addTaskGui.getRoot());
    }

    @Override
    public void requestTextFieldFocus() {
        addTaskGui.createTextField();
    }
}
