package gui;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import task.Task;

public class PushTaskBlockGui extends GuiComponent<VBox> {

    private static final String FXML_RESOURCE = "PushTaskBlockGui.fxml";
    private static final String NO_TASKS_MESSAGE = "No tasks found";

    @FXML
    private VBox block;
    @FXML
    private Label blockHeaderLabel;
    @FXML
    private ToggleButton selectButton;

    private final List<Task> tasks;
    private final List<PushTaskGui> taskGuis;
    private final ChangeListener<Boolean> changeListener =
            (obs, oldBool, newBool) -> registerChange(newBool);

    private boolean isSettingSubButtons;

    /**
     * Creates an individual push block with the given {@code blockName} header.
     * It contains the {@code tasks} marked by default as {@code isSelected}.
     */
    public PushTaskBlockGui(String blockName, List<Task> tasks, boolean isSelectedByDefault) {
        super(FXML_RESOURCE);
        blockHeaderLabel.setText(blockName);
        this.tasks = tasks;
        selectButton.setSelected(isSelectedByDefault);
        taskGuis = new ArrayList<>();
        isSettingSubButtons = false;
        init();
    }

    /**
     * Initialises the taskGuis for this block.
     */
    public void init() {
        if (tasks.size() == 0) {
            block.getChildren().add(new Label(NO_TASKS_MESSAGE));
            return;
        }

        tasks.forEach(task -> taskGuis.add(new PushTaskGui(
                selectButton.isSelected(),
                task,
                changeListener)));
        taskGuis.forEach(taskGui -> block.getChildren().add(taskGui.getRoot()));
    }

    @FXML
    private void registerSelection() {
        isSettingSubButtons = true;
        if (!selectButton.isSelected()) {
            taskGuis.forEach(PushTaskGui::select);
        } else {
            taskGuis.forEach(PushTaskGui::unselect);
        }
        isSettingSubButtons = false;
    }

    private void registerChange(boolean isSelected) {
        if (!isSelected && !isSettingSubButtons) {
            selectButton.setSelected(isSelected);
        }
    }

    public boolean isIndexSelected(int index) {
        return taskGuis.get(index).isSelected();
    }
}
