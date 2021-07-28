package storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import task.TaskBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonTaskBlock {

    private final String blockHeader;
    private final List<JsonTask> tasks;

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
