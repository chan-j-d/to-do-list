package gui;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public abstract class GuiComponent<T> {

    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private final T root;

    public GuiComponent(String resource) {
        fxmlLoader.setLocation(getClass().getResource(resource));
        T temp = null;
        try {
            temp = fxmlLoader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        root = temp;
        init();
    }

    public T getRoot() {
        return root;
    }

    public abstract void init();

}
