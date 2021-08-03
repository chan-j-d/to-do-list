package gui;

import javafx.scene.Parent;

public class MainWindow extends GuiComponent {

    private static final String FXML_RESOURCE = "MainWindow.fxml";

    public MainWindow(Object root) {
        super(FXML_RESOURCE, root);
    }

}
