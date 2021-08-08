package gui;

import command.CompleteTaskCommand;
import command.DeleteTaskCommand;
import command.UndoTaskCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import task.Task;

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

    private final String taskBlockName;
    private final int index;

    public TaskGui(String taskBlockName, int index, Task task) {
        super(FXML_RESOURCE);
        this.taskBlockName = taskBlockName;
        this.index = index;
        taskDescriptionLabel.setText(task.getDescription());
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


}
