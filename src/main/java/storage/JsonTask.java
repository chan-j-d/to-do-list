package storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import task.Task;

public class JsonTask {

    private final String description;

    @JsonCreator
    public JsonTask(@JsonProperty("description") String description) {
        this.description = description;
    }

    public Task toJavaType() {
        return new Task(description);
    }

}
