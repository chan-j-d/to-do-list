package storage;

import static task.BlockNames.BLOCK_NAMES;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import task.TaskBlock;
import task.TaskList;

public class JsonTaskList implements JsonStorer<TaskList> {

    private final Map<String, JsonTaskBlock> blocks;


    /**
     * Creates a JSON equivalent object of a {@code TaskList} for saving.
     */
    public JsonTaskList(@JsonProperty("blocks") Map<String, JsonTaskBlock> blocks) {
        this.blocks = new LinkedHashMap<>();
        for (String key : blocks.keySet()) {
            this.blocks.put(key, blocks.get(key));
        }
    }

    @Override
    public TaskList toJavaType() {
        Map<String, TaskBlock> nonJsonBlocks = new LinkedHashMap<>();
        for (String day : BLOCK_NAMES) {
            nonJsonBlocks.put(day, blocks.get(day).toJavaType());
        }
        return new TaskList(nonJsonBlocks);
    }

    public static JsonTaskList convertToJson(TaskList taskList) {
        return new JsonTaskList(taskList.getBlocksMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> JsonTaskBlock.convertToJson(entry.getValue()))));
    }

    @Override
    public String toString() {
        return "Json Task List: \n" + blocks;
    }

}
