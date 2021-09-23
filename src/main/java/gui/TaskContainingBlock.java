package gui;

import javafx.scene.layout.VBox;
import task.Task;

import java.util.List;

public abstract class TaskContainingBlock extends GuiComponent<VBox> {

    /**
     * Creates a new gui component loaded from an {@code resource}.
     */
    public TaskContainingBlock(String resource) {
        super(resource);
    }

    public abstract void replaceExistingTasks(List<Task> tasks);
}
