package logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LogsManager {

    private static final long LIMIT = (1 << 20);
    private static final int FILE_COUNT = 1;
    private static final Formatter FORMATTER = new LogFormatter();
    private static final List<Logger> LOGGERS = new ArrayList<>();

    private static Handler FILE_HANDLER;

    public static Logger getLogger(String loggerName) {
        Logger logger = Logger.getLogger(loggerName);
        logger.setUseParentHandlers(false);

        logger.addHandler(FILE_HANDLER);
        LOGGERS.add(logger);
        return logger;
    }

    public static <T> Logger getLogger(Class<T> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static void setLogFilePath(String logFilePath) throws IOException {
        FILE_HANDLER = new FileHandler(logFilePath,
                LIMIT,
                FILE_COUNT,
                true);
        FILE_HANDLER.setFormatter(FORMATTER);
        LOGGERS.forEach(logger -> logger.addHandler(FILE_HANDLER));
    }
}
