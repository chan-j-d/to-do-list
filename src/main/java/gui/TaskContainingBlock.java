package gui;

import java.util.List;
import javafx.scene.layout.VBox;
import task.Task;

public abstract class TaskContainingBlock extends GuiComponent<VBox> {

    /**
     * Creates a new gui component loaded from an {@code resource}.
     */
    public TaskContainingBlock(String resource) {
        super(resource);
    }

    /**
     * Replaces existing tasks with that in {@code tasks}.
     */
    public abstract void replaceExistingTasks(List<Task> tasks);

    /**
     * Requests focus on the text field.
     */
    public abstract void requestTextFieldFocus();
}
