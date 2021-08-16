package task;

import java.util.List;

public class BlockNames {

    /**
     * Standard list of block names.
     */
    public static final List<String> BLOCK_NAMES = List.of("monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday", "sunday");

    public static boolean isValidBlockName(String name) {
        return !BLOCK_NAMES.contains(name.toLowerCase());
    }
}
