package gui;

import static task.BlockNames.DAYS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.Task;
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
    private Map<String, TaskContainingBlock> nameToBlockMap;

    public MainWindow() {
        super(FXML_RESOURCE);
    }

    /**
     * Updates the current window view with the provided {@code taskList}.
     */
    public void updateWindow(TaskList taskList) {
        final double currentVValue = scrollPane.getVvalue();
        List<Node> nodeList = new ArrayList<>();
        nameToBlockMap = new HashMap<>();
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
        List<Task> tasks = taskList.getTasksInBlock(blockName);
        Platform.runLater(() -> {
            nameToBlockMap.get(blockName).replaceExistingTasks(tasks);
            taskListGui.requestFocus();
        });
    }

    public void removeBlock(TaskList taskList, String blockName) {
        final double currentVValue = scrollPane.getVvalue();
        Platform.runLater(() -> {
            GuiComponent<VBox> block = nameToBlockMap.remove(blockName);
            taskListGui.getChildren().remove(block.getRoot());
            lowerAddTaskBlock.setIndex(taskListGui.getChildren().size());

            // Changes scrollPane height to value before update
            scrollPane.setVvalue(currentVValue);
            scrollPane.requestFocus();
        });
    }

    public void addBlock(TaskList taskList, int index) {
        Node nodeToAdd = createTaskBlock(taskList, index);
        Platform.runLater(() -> {
            taskListGui.getChildren().add(index, nodeToAdd);
            nodeToAdd.requestFocus();
            lowerAddTaskBlock.setIndex(taskListGui.getChildren().size());
        });
    }

    private Node createTaskBlock(TaskList taskList, int index) {
        String blockName = taskList.getBlock(index).getBlockName();
        TaskContainingBlock block;
        if (DAYS.contains(blockName)) {
            block = new TaskBlockGui(taskList.getBlock(index));
        } else {
            block = new DeletableTaskBlockGui(taskList.getBlock(index));
        }
        nameToBlockMap.put(blockName, block);
        return block.getRoot();
    }

    private Node createTaskBlock(TaskList taskList, String blockName) {
        TaskContainingBlock block;
        if (DAYS.contains(blockName)) {
            block = new TaskBlockGui(taskList.getBlock(blockName));
        } else {
            block = new DeletableTaskBlockGui(taskList.getBlock(blockName));
        }
        nameToBlockMap.put(blockName, block);
        return block.getRoot();
    }
}
