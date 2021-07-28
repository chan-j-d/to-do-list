package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.TaskBlock;
import task.TaskList;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        for (String key : blocks.keySet()) {
            nonJsonBlocks.put(key, blocks.get(key).toJavaType());
        }
        return new TaskList(nonJsonBlocks);
    }

    public static JsonTaskList convertToJson(TaskList taskList) {
        return new JsonTaskList(taskList.getBlocksMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> JsonTaskBlock.convertToJson(entry.getValue()))));
    }



}
