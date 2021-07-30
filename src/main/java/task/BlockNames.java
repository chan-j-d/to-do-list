package task;

import java.util.List;

public class BlockNames {

    public static final List<String> BLOCK_NAMES = List.of("monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday", "sunday");

    public static boolean isValidBlockName(String blockName) {
        return BLOCK_NAMES.contains(blockName);
    }
}
