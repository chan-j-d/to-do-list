import gui.MainApplication;
import javafx.application.Application;
import logging.LogsManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = LogsManager.getLogger(Main.class);
    private static final String ERROR_LAUNCHING_GUI = "Error launching gui for application.";

    public static void main(String[] args) {
        try {
            Application.launch(MainApplication.class, args);
        } catch (RuntimeException re) {
            LOGGER.log(Level.SEVERE, ERROR_LAUNCHING_GUI,re);
        }
    }
}
