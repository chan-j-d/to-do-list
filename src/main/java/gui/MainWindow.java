package gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.TaskBlock;
import task.TaskList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static task.BlockNames.BLOCK_NAMES;

public class MainWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "MainWindow.fxml";

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox taskListGui;


    public MainWindow() {
        super(FXML_RESOURCE);
    }

    public void updateWindow(TaskList taskList) {
        Map<String, TaskBlock> blockMap = taskList.getBlocksMap();
        List<Node> nodeList = BLOCK_NAMES.stream()
                .map(blockMap::get)
                .map(TaskBlockGui::new)
                .map(GuiComponent::getRoot)
                .collect(Collectors.toList());
        taskListGui.getChildren().clear();
        taskListGui.getChildren().addAll(nodeList);
    }


}
