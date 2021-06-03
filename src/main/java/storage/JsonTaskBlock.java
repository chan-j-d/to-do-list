package storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import task.TaskBlock;

import java.util.ArrayList;
import java.util.List;

public class JsonTaskBlock {

    private final String blockHeader;
    private final List<JsonTask> tasks;

    @JsonCreator
    public JsonTaskBlock(@JsonProperty("blockHeader") String blockHeader,
            @JsonProperty("tasks") List<JsonTask> tasks) {
        this.blockHeader = blockHeader;
        this.tasks = new ArrayList<>();
        tasks.addAll(tasks);
    }

    public TaskBlock toJavaType() {
        return new TaskBlock(blockHeader, tasks);
    }

}
