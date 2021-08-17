package logging;

import java.util.Arrays;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    private static final String MESSAGE_FORMAT = "[ENTRY]\nTime: %s\n[%s] %s\n%s\n\n";

    @Override
    public String format(LogRecord record) {
        return String.format(MESSAGE_FORMAT,
                record.getInstant(),
                record.getLevel(),
                record.getLoggerName(),
                formatMessage(record) + formatStackTrace(record));
    }

    private String formatStackTrace(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ERROR MESSAGE] " + record.getThrown().getMessage());
        Arrays.stream(record.getThrown().getStackTrace())
                .forEach(element -> sb.append('\n' + element.toString()));
        return sb.toString();
    }
}
