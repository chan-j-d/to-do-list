import gui.MainApplication;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import logging.LogsManager;

public class Main {

    private static final String SAVEFILE_DIRECTORY = "to-do-list";
    private static final String LOGFILE_NAME = "todolist-logs";
    private static final Path DEFAULT_LOG_PATH = Paths.get(SAVEFILE_DIRECTORY, LOGFILE_NAME);
    private static final String ERROR_LAUNCHING_GUI = "Error launching gui for application.";

    private static Logger LOGGER;

    /**
     * Launches the main GUI application. Sets the logfile path before launching.
     */
    public static void main(String[] args) {
        try {
            LogsManager.setLogFilePath(DEFAULT_LOG_PATH.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace(); // no gui has been initialised
        }

        LOGGER = LogsManager.getLogger(Main.class);

        try {
            Application.launch(MainApplication.class, SAVEFILE_DIRECTORY);
        } catch (RuntimeException re) {
            LOGGER.log(Level.SEVERE, ERROR_LAUNCHING_GUI,re);
            re.printStackTrace();
        }
    }
}
