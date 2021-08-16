package storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import task.TaskBlock;

public class JsonTaskBlock implements JsonStorer<TaskBlock> {

    private final String blockHeader;
    private final List<JsonTask> tasks;

    /**
     * Creates a JSON equivalent object of a {@code TaskBlock} for saving.
     */
    @JsonCreator
    public JsonTaskBlock(@JsonProperty("blockHeader") String blockHeader,
            @JsonProperty("tasks") List<JsonTask> tasks) {
        this.blockHeader = blockHeader;
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    public TaskBlock toJavaType() {
        return new TaskBlock(blockHeader,
                tasks.stream().map(jsonTask -> jsonTask.toJavaType()).collect(Collectors.toList()));
    }

    public static JsonTaskBlock convertToJson(TaskBlock taskBlock) {
        return new JsonTaskBlock(taskBlock.getBlockName(),
                taskBlock.getTasks().stream().map(task -> JsonTask.convertToJson(task)).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return String.format("Json Task Block (%s): %s", blockHeader, tasks);
    }

}
