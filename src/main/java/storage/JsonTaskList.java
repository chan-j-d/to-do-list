package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.TaskBlock;
import task.TaskList;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static task.BlockNames.BLOCK_NAMES;

public class JsonTaskList {

    private final Map<String, JsonTaskBlock> blocks;

    public JsonTaskList (@JsonProperty("blocks") Map<String, JsonTaskBlock> blocks) {
        this.blocks = new LinkedHashMap<>();
        for (String key : blocks.keySet()) {
            this.blocks.put(key, blocks.get(key));
        }
    }

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
