package util;

import java.io.IOException;
import logging.LogsManager;

public class TestUtil {

    private static final String DEFAULT_LOG_PATH = "build/test-results/logs.txt";

    /**
     * Sets the logs directory to a default path.
     * Meant for allowing tests to run where methods might involve some logging.
     */
    public static void setLogsDirectory() {
        try {
            LogsManager.setLogFilePath(DEFAULT_LOG_PATH.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
