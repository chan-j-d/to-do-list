package gui;

import static task.TaskBlock.STARTING_COUNT;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskBlock;

public class TaskBlockGui extends TaskContainingBlock {

    private static final String FXML_RESOURCE = "TaskBlockGui.fxml";

    @FXML
    private VBox block;
    @FXML
    private Label blockHeaderLabel;
    @FXML
    private Region line;

    private String blockHeader;
    private final TaskBlock taskBlock;
    private final AddTaskGui addTaskGui;

    /**
     * Creates a singular task block gui from the given {@code taskBlock}.
     */
    public TaskBlockGui(TaskBlock taskBlock) {
        super(FXML_RESOURCE);
        blockHeader = taskBlock.getBlockName();
        this.taskBlock = taskBlock;
        this.addTaskGui = new AddTaskGui(blockHeader);
        init();
    }

    /**
     * Initialises the necessary values that cannot be done in the FXML file easily.
     * Sets block-header and fills up block with existing tasks in the task block.
     */
    public void init() {
        blockHeaderLabel.setText(capitalizeString(blockHeader));
        int index = STARTING_COUNT;
        for (Task task : taskBlock.getTasks()) {
            block.getChildren().add(new TaskGui(blockHeader, index++, task).getRoot());
        }
        block.getChildren().add(addTaskGui.getRoot());
    }

    private String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    @Override
    public void replaceExistingTasks(List<Task> tasks) {
        int index = STARTING_COUNT;
        block.getChildren().clear();
        block.getChildren().addAll(blockHeaderLabel, line);
        for (Task task : taskBlock.getTasks()) {
            TaskGui newTaskGui = new TaskGui(blockHeader, index++, task);
            block.getChildren().add(newTaskGui.getRoot());
        }
        block.getChildren().add(addTaskGui.getRoot());
    }

    @Override
    public void requestTextFieldFocus() {
        addTaskGui.createTextField();
    }
}
