package logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    private static final String MESSAGE_FORMAT = "Time: %s\n[%s] %s\n%s";

    @Override
    public String format(LogRecord record) {
        return String.format(MESSAGE_FORMAT,
                record.getInstant(),
                record.getLevel(),
                record.getLoggerName(),
                formatMessage(record));
    }
}
