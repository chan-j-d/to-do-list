package gui;

import static task.BlockNames.DAYS;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.TaskList;

public class MainWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "MainWindow.fxml";
    private static final int ADD_BLOCK_UPPER_INDEX = 0;

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
        final double currentVValue = scrollPane.getVvalue();
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Node nodeToAdd = createTaskBlock(taskList, i);
            nodeList.add(nodeToAdd);
        }

        nodeList.add(0, new AddTaskBlockGui(ADD_BLOCK_UPPER_INDEX).getRoot());
        nodeList.add(new AddTaskBlockGui(nodeList.size() - 1).getRoot());

        Platform.runLater(() -> {
            // Replaces current nodes
            taskListGui.getChildren().clear();
            taskListGui.getChildren().addAll(nodeList);

            // Changes scrollPane height to value before update
            scrollPane.setVvalue(currentVValue);
            scrollPane.requestFocus();
        });
    }

    public void updateUserBlock(TaskList taskList, int index) {
        Node nodeToAdd = createTaskBlock(taskList, index);
        taskListGui.getChildren().set(index, nodeToAdd);
    }

    public void removeBlock(TaskList taskList, int index) {
        taskListGui.getChildren().remove(index);
    }

    public void addBlock(TaskList taskList, int index) {
        Node nodeToAdd = createTaskBlock(taskList, index);
        taskListGui.getChildren().add(nodeToAdd);
    }

    private Node createTaskBlock(TaskList taskList, int index) {
        String blockName = taskList.getBlock(index).getBlockName();
        if (DAYS.contains(blockName)) {
            return new TaskBlockGui(taskList.getBlock(index)).getRoot();
        } else {
            return new DeletableTaskBlockGui(taskList.getBlock(index), index).getRoot();
        }
    }
}
