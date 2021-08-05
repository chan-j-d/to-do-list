package gui;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public abstract class GuiComponent<T> {

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    public GuiComponent(String resource) {
        fxmlLoader.setLocation(getClass().getResource(resource));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public T getRoot() {
        return fxmlLoader.getRoot();
    }

}
