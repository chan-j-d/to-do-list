package gui;

import static task.BlockNames.DAYS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    private VBox wrapperBox;
    @FXML
    private VBox taskListGui;
    @FXML
    private ScrollPane scrollPane;

    private AddTaskBlockGui lowerAddTaskBlock;
    private Map<String, GuiComponent<VBox>> nameToBlockMap;

    public MainWindow() {
        super(FXML_RESOURCE);
        nameToBlockMap = new HashMap<>();
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

        wrapperBox.getChildren().add(0, new AddTaskBlockGui(ADD_BLOCK_UPPER_INDEX).getRoot());
        lowerAddTaskBlock = new AddTaskBlockGui(nodeList.size());
        wrapperBox.getChildren().add(lowerAddTaskBlock.getRoot());

        Platform.runLater(() -> {
            // Replaces current nodes
            taskListGui.getChildren().clear();
            taskListGui.getChildren().addAll(nodeList);

            // Changes scrollPane height to value before update
            scrollPane.setVvalue(currentVValue);
            scrollPane.requestFocus();
        });
    }

    public void updateUserBlock(TaskList taskList, String blockName) {
        Node nodeToAdd = createTaskBlock(taskList, blockName);
        int index = taskList.indexOf(blockName);
        Platform.runLater(() -> {
            taskListGui.getChildren().set(index, nodeToAdd);
            taskListGui.requestFocus();
        });
    }

    public void removeBlock(TaskList taskList, String blockName) {
        Platform.runLater(() -> {
            GuiComponent<VBox> block = nameToBlockMap.remove(blockName);
            taskListGui.getChildren().remove(block.getRoot());
            lowerAddTaskBlock.setIndex(taskListGui.getChildren().size());
        });
    }

    public void addBlock(TaskList taskList, int index) {
        Node nodeToAdd = createTaskBlock(taskList, index);
        Platform.runLater(() -> {
            taskListGui.getChildren().add(index, nodeToAdd);
            lowerAddTaskBlock.setIndex(taskListGui.getChildren().size());
        });
    }

    private Node createTaskBlock(TaskList taskList, int index) {
        String blockName = taskList.getBlock(index).getBlockName();
        GuiComponent<VBox> block;
        if (DAYS.contains(blockName)) {
            block = new TaskBlockGui(taskList.getBlock(index));
        } else {
            block = new DeletableTaskBlockGui(taskList.getBlock(index));
        }
        nameToBlockMap.put(blockName, block);
        return block.getRoot();
    }

    private Node createTaskBlock(TaskList taskList, String blockName) {
        GuiComponent<VBox> block;
        if (DAYS.contains(blockName)) {
            block = new TaskBlockGui(taskList.getBlock(blockName));
        } else {
            block = new DeletableTaskBlockGui(taskList.getBlock(blockName));
        }
        nameToBlockMap.put(blockName, block);
        return block.getRoot();
    }
}
