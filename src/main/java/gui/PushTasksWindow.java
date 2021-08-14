package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class PushTasksWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "PushTasksWindow.fxml";
    private static final boolean DEFAULT_BOOLEAN = true;
    private static final String STRING_COMPLETED_HEADER = "Completed tasks";
    private static final String STRING_INCOMPLETE_HEADER = "Incomplete tasks";

    @FXML
    private VBox prevDayTasksHolder;

    private final List<Task> tasks;

    public PushTasksWindow(List<Task> tasks) {
        super(FXML_RESOURCE);
        this.tasks = tasks;
        init();
    }

    private void init() {
        List<Task> completedTasks = new ArrayList<>();
        List<Task> incompleteTasks = new ArrayList<>();
        tasks.forEach(task -> {
            if (task.isDone()) {
                completedTasks.add(task);
            } else {
                incompleteTasks.add(task);
            }
        });
        prevDayTasksHolder.getChildren().add(0,
                new PushTaskBlockGui(STRING_INCOMPLETE_HEADER, incompleteTasks, DEFAULT_BOOLEAN)
                        .getRoot());
        prevDayTasksHolder.getChildren().add(0,
                new PushTaskBlockGui(STRING_COMPLETED_HEADER, completedTasks, DEFAULT_BOOLEAN)
                        .getRoot());

    }

    @FXML
    private void registerMove() {

    }
}
