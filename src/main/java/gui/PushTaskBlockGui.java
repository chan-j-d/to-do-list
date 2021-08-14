package gui;

import javafx.beans.InvalidationListener;
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
    private Label blockLabelHeader;
    @FXML
    private ToggleButton selectButton;

    private final List<Task> tasks;
    private final List<PushTaskGui> taskGuis;
    private final InvalidationListener INVALIDATION_LISTENER =
            x -> registerUnselect();
    private boolean isSelected;

    public PushTaskBlockGui(String blockName, List<Task> tasks, boolean isSelectedByDefault) {
        super(FXML_RESOURCE);
        blockLabelHeader.setText(blockName);
        this.tasks = tasks;
        isSelected = isSelectedByDefault;
        selectButton.setSelected(isSelected);
        taskGuis = new ArrayList<>();
        init();
    }

    public void init() {
        tasks.forEach(task -> taskGuis.add(new PushTaskGui(
                isSelected,
                task,
                INVALIDATION_LISTENER)));
        taskGuis.forEach(taskGui -> block.getChildren().add(taskGui.getRoot()));
    }

    @FXML
    private void registerSelection() {
        isSelected = true;
        taskGuis.forEach(PushTaskGui::select);
    }

    private void registerUnselect() {
        if (isSelected) {
            isSelected = false;
            selectButton.setSelected(isSelected);
        }
    }
}
