package gui;

import javafx.beans.InvalidationListener;
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
    private Label taskDescriptionLabel;
    @FXML
    private ToggleButton selectButton;

    public PushTaskGui(boolean isSelected, Task task, ChangeListener<? super Boolean> listener) {
        super(FXML_RESOURCE);
        taskDescriptionLabel.setText(task.getDescription());
        selectButton.setSelected(isSelected);
        selectButton.selectedProperty().addListener(listener);
    }

    @FXML
    private void registerToggle() {
        selectButton.setSelected(selectButton.isSelected());
    }

    public void select() {
        selectButton.setSelected(true);
    }

    public void unselect() {
        selectButton.setSelected(false);
    }
}
