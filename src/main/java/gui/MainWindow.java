package gui;

import static task.BlockNames.DAYS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.BlockNames;
import task.TaskBlock;
import task.TaskList;

public class MainWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "MainWindow.fxml";

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox taskListGui;
    @FXML
    private ScrollPane scrollPane;

    public MainWindow() {
        super(FXML_RESOURCE);
    }

    /**
     * Updates the current window view with the provided {@code taskList}.
     */
    public void updateWindow(TaskList taskList) {
        double currentVValue = scrollPane.getVvalue();
        Map<String, TaskBlock> blockMap = taskList.getBlocksMap();
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String blockName = taskList.getBlock(i).getBlockName();
            Node node;
            if (DAYS.contains(blockName)) {
                node = new TaskBlockGui(taskList.getBlock(i)).getRoot();
            } else {
                node = new DeletableTaskBlockGui(taskList.getBlock(i), i).getRoot();
            }
            nodeList.add(node);
        }

        Platform.runLater(() -> {
            // Replaces current nodes
            taskListGui.getChildren().clear();
            taskListGui.getChildren().addAll(nodeList);

            // Changes scrollPane height to value before update
            scrollPane.setVvalue(currentVValue);
            scrollPane.requestFocus();
        });
    }
}
