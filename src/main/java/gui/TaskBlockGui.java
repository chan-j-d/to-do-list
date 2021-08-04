package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import task.Task;
import task.TaskBlock;

public class TaskBlockGui extends GuiComponent<VBox> {

    private static final String FXML_RESOURCE = "TaskBlockGui.fxml";

    @FXML
    private VBox block;
    @FXML
    private String blockHeader;
    private final TaskBlock taskBlock;

    public TaskBlockGui(TaskBlock taskBlock) {
        super(FXML_RESOURCE);
        blockHeader = taskBlock.getBlockName();
        this.taskBlock = taskBlock;
        init();
    }

    private void init() {
        for (Task task : taskBlock.getTasks()) {
            block.getChildren().add(new Text(task.getDescription()));
        }
    }


}
