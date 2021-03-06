package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import task.TaskBlock;
import task.TaskList;

public class JsonTaskList implements JsonStorer<TaskList> {

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

        fixBlockMapping();
    }

    @Override
    public TaskList toJavaType() {
        Map<String, TaskBlock> map = blocks.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toJavaType()));
        return new TaskList(keyOrder, map);
    }

    /**
     * Converts the given {@code taskList} into the type {@code JsonTaskList} for storage.
     */
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

    private void fixBlockMapping() {
        Set<String> keySet = Set.copyOf(blocks.keySet());
        for (String key : keySet) {
            if (!keyOrder.contains(key)) {
                keyOrder.add(key);
            }
        }

        for (String keyOrdering : keyOrder) {
            if (!keySet.contains(keyOrdering)) {
                blocks.put(keyOrdering, new JsonTaskBlock(keyOrdering, new ArrayList<>()));
            }
        }
    }
}
