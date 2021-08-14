package gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import task.Task;

public class PushTaskGui extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "PushTaskGui.fxml";

    @FXML
    private ToggleButton doneButton;
    @FXML
    private Label taskDescriptionLabel;
    @FXML
    private ToggleButton selectButton;

    private boolean isSelected;

    public PushTaskGui(boolean isSelected, Task task, ChangeListener<? super Boolean> listener) {
        super(FXML_RESOURCE);
        this.isSelected = isSelected;
        taskDescriptionLabel.setText(task.getDescription());
        doneButton.setSelected(task.isDone());
        selectButton.setSelected(isSelected);
        selectButton.selectedProperty().addListener(listener);
    }

    public void registerSelection() {
        isSelected = !isSelected;
        selectButton.setSelected(isSelected);
    }
}
