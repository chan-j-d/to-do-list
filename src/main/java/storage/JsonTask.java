package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.Task;

public class JsonTask {

    private final String description;
    private final boolean isDone;

    public JsonTask(@JsonProperty("description") String description,
            @JsonProperty("isDone") boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task toJavaType() {
        return new Task(description, isDone);
    }

    public static JsonTask convertToJson(Task task) {
        return new JsonTask(task.getDescription(), task.isDone());
    }

    @Override
    public String toString() {
        return "Json Task: " + description + " " + isDone;
    }

}
