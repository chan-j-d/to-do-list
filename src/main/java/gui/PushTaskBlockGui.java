package gui;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class PushTaskBlockGui extends GuiComponent<VBox> {

    private static final String FXML_RESOURCE = "PushTaskBlockGui.fxml";

    @FXML
    private VBox block;
    @FXML
    private Label blockHeaderLabel;
    @FXML
    private ToggleButton selectButton;

    private final List<Task> tasks;
    private final List<PushTaskGui> taskGuis;
    private final ChangeListener<Boolean> CHANGE_LISTENER =
            (obs, oldBool, newBool) -> registerChange(newBool);

    private boolean isSettingSubButtons;

    public PushTaskBlockGui(String blockName, List<Task> tasks, boolean isSelectedByDefault) {
        super(FXML_RESOURCE);
        blockHeaderLabel.setText(blockName);
        this.tasks = tasks;
        selectButton.setSelected(isSelectedByDefault);
        taskGuis = new ArrayList<>();
        isSettingSubButtons = false;
        init();
    }

    public void init() {
        tasks.forEach(task -> taskGuis.add(new PushTaskGui(
                selectButton.isSelected(),
                task,
                CHANGE_LISTENER)));
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
}
