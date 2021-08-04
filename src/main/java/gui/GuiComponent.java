package gui;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GuiComponent<T> {

    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private final T root;

    public GuiComponent(String resource) {
        fxmlLoader.setLocation(getClass().getResource(resource));
        fxmlLoader.setController(this);
        T temp = null;
        try {
            temp = fxmlLoader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        root = temp;
    }

    public T getRoot() {
        return root;
    }

}
