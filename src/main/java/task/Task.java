package task;

public class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    private char getCompletionSymbol() {
        return isDone ? '\u2713' : '\u2717';
    }

    @Override
    public String toString() {
        return description + String.format(" [%c]", getCompletionSymbol());
    }

}
