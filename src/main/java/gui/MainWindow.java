package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.TaskBlock;
import task.TaskList;

import java.util.Map;

import static task.BlockNames.BLOCK_NAMES;

public class MainWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "MainWindow.fxml";

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox taskListGui;

    private final TaskList taskList;

    public MainWindow(TaskList taskList) {
        super(FXML_RESOURCE);
        this.taskList = taskList;
    }

    @Override
    public void init() {
        Map<String, TaskBlock> blockMap = taskList.getBlocksMap();
        for (String blockName : BLOCK_NAMES) {
            TaskBlock currentBlock = blockMap.get(blockName);
            TaskBlockGui currentGuiBlock = new TaskBlockGui(currentBlock);
            taskListGui.getChildren().add(currentGuiBlock.getRoot());
        }
    }

}
