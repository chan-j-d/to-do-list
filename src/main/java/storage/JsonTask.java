package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.Task;

public class JsonTask {

    private final String description;

    public JsonTask(@JsonProperty("description") String description) {
        this.description = description;
    }

    public Task toJavaType() {
        return new Task(description);
    }

    public static JsonTask convertToJson(Task task) {
        return new JsonTask(task.getDescription());
    }

    @Override
    public String toString() {
        return "Json Task: " + description;
    }

}
