package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.TaskBlock;
import task.TaskList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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



}
