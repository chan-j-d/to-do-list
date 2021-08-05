package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import task.Task;
import task.TaskBlock;

public class TaskBlockGui extends GuiComponent<VBox> {

    private static final String FXML_RESOURCE = "TaskBlockGui.fxml";
    private static final double DEFAULT_HEADER_SIZE = 40.0;
    private static final double DEFAULT_TASK_SIZE = 30.0;


    @FXML
    private VBox block;
    @FXML
    private Label blockHeaderLabel;

    private String blockHeader;
    private final TaskBlock taskBlock;

    public TaskBlockGui(TaskBlock taskBlock) {
        super(FXML_RESOURCE);
        blockHeader = taskBlock.getBlockName();
        this.taskBlock = taskBlock;
        init();
    }

    public void init() {
        blockHeaderLabel.setText(capitalizeString(blockHeader));
        for (Task task : taskBlock.getTasks()) {
            block.getChildren().add(new Text(task.getDescription()));
        }
        getRoot().setPrefHeight(calculatePrefHeight());
    }

    private String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    private double calculatePrefHeight() {
        return DEFAULT_HEADER_SIZE + (taskBlock.getNumTasks() == 0 ? DEFAULT_TASK_SIZE
                : taskBlock.getNumTasks() * DEFAULT_TASK_SIZE);
    }

}
