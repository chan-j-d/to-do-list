package gui;

import static task.TaskBlock.STARTING_COUNT;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskBlock;

public class TaskBlockGui extends GuiComponent<VBox> {

    private static final String FXML_RESOURCE = "TaskBlockGui.fxml";

    @FXML
    private VBox block;
    @FXML
    private Label blockHeaderLabel;

    private String blockHeader;
    private final TaskBlock taskBlock;

    /**
     * Creates a singular task block gui from the given {@code taskBlock}.
     */
    public TaskBlockGui(TaskBlock taskBlock) {
        super(FXML_RESOURCE);
        blockHeader = taskBlock.getBlockName();
        this.taskBlock = taskBlock;
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
        block.getChildren().add(new AddTaskBlockGui(blockHeader).getRoot());
    }

    private String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

}
