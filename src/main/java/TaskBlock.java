import java.util.ArrayList;
import java.util.List;

public class TaskBlock {

    private String blockName;
    private List<Task> tasks;

    public TaskBlock(String blockName) {
        this.blockName = blockName;
        tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return blockName +
                tasks.stream().reduce("",
                        (x, y) -> x + "\n" + y,
                        (x, y) -> x + "\n" + y);
    }

}
