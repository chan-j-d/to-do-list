package util;

import java.util.List;

public class DateUtil {

    public static final List<String> DAY_NAMES = List.of("monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday", "sunday");
    public static final int NUM_DAYS_IN_WEEK = 7;

    public static String getNextDay(String day) {
        int currentIndex = DAY_NAMES.indexOf(day);

        return currentIndex == NUM_DAYS_IN_WEEK - 1
                ? DAY_NAMES.get(0)
                : DAY_NAMES.get(currentIndex + 1);
    }
}
