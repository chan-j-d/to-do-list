package util;

import logging.LogsManager;

import java.io.IOException;

public class TestUtil {

    private static final String DEFAULT_LOG_PATH = "build/test-results/logs.txt";

    public static void setLogsDirectory() {
        try {
            LogsManager.setLogFilePath(DEFAULT_LOG_PATH.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
