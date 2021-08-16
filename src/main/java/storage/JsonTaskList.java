package storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import task.TaskBlock;
import task.TaskList;

import static task.BlockNames.DAYS;

public class JsonTaskList {

    private final Map<String, JsonTaskBlock> blocks;
    private final List<String> keyOrder;

    /**
     * Creates a JSON equivalent object of a {@code TaskList} for saving.
     */
    public JsonTaskList(@JsonProperty("keyOrder") List<String> keyOrder,
            @JsonProperty("blocks") Map<String, JsonTaskBlock> blocks) {
        this.keyOrder = new ArrayList<>();
        this.keyOrder.addAll(keyOrder);

        this.blocks = new HashMap<>();
        this.blocks.putAll(blocks);
    }

    public TaskList toJavaType() {
        Map<String, TaskBlock> map = blocks.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toJavaType()));
        return new TaskList(keyOrder, map);
    }

    public static JsonTaskList convertToJson(TaskList taskList) {
        return new JsonTaskList(taskList.getKeyOrder(),
                taskList.getBlocksMap().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                entry -> JsonTaskBlock.convertToJson(entry.getValue()))));
    }

    @Override
    public String toString() {
        return "Json Task List: \n" + blocks;
    }

}
