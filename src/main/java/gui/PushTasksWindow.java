package gui;

import command.Command;
import command.DeleteTaskCommand;
import command.MoveTaskCommand;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static task.TaskBlock.STARTING_COUNT;

public class PushTasksWindow extends GuiComponent<AnchorPane> {

    private static final String FXML_RESOURCE = "PushTasksWindow.fxml";
    private static final boolean DEFAULT_BOOLEAN = true;
    private static final String STRING_COMPLETED_HEADER = "Completed tasks";
    private static final String STRING_INCOMPLETE_HEADER = "Incomplete tasks";

    @FXML
    private VBox prevDayTasksHolder;

    private final List<Task> tasks;
    private final Map<Integer, Integer> originalIndexToPushIndexMap;
    private final Map<Integer, Integer> originalIndexToDeleteIndexMap;
    private final String prevDay;
    private final String currentDay;
    private PushTaskBlockGui incompleteBlock;
    private PushTaskBlockGui completedBlock;

    public PushTasksWindow(List<Task> tasks, String prevDay, String currentDay) {
        super(FXML_RESOURCE);
        this.tasks = tasks;
        this.prevDay = prevDay;
        this.currentDay = currentDay;
        originalIndexToPushIndexMap = new HashMap<>();
        originalIndexToDeleteIndexMap = new HashMap<>();
        init();
    }

    private void init() {
        List<Task> completedTasks = new ArrayList<>();
        List<Task> incompleteTasks = new ArrayList<>();
        int originalListIndex = STARTING_COUNT;
        int completedNewIndex = 0;
        int incompleteNewIndex = 0;
        for (Task task : tasks) {
            if (!task.isDone()) {
                incompleteTasks.add(task);
                originalIndexToPushIndexMap.put(originalListIndex++, completedNewIndex++);
            } else {
                completedTasks.add(task);
                originalIndexToDeleteIndexMap.put(originalListIndex++, incompleteNewIndex++);
            }
        }

        incompleteBlock =  new PushTaskBlockGui(STRING_INCOMPLETE_HEADER, incompleteTasks, DEFAULT_BOOLEAN);
        completedBlock = new PushTaskBlockGui(STRING_COMPLETED_HEADER, completedTasks, DEFAULT_BOOLEAN);
        prevDayTasksHolder.getChildren().add(0, incompleteBlock.getRoot());
        prevDayTasksHolder.getChildren().add(0, completedBlock.getRoot());
    }

    @FXML
    private void registerMove() {
        List<Command<TaskList>> commands = new ArrayList<>();

        List<Integer> indicesToMove = getIndicesToMove();
        List<Integer> indicesToDelete = getIndicesToDelete();
        List<Integer> allIndices = new ArrayList<>();
        allIndices.addAll(indicesToMove);
        allIndices.addAll(indicesToDelete);
        allIndices.sort(Comparator.reverseOrder());

        for (int index : allIndices) {
            if (indicesToMove.contains(index)) {
                commands.add(new MoveTaskCommand(prevDay, index, currentDay));
            } else {
                commands.add(new DeleteTaskCommand(prevDay, index));
            }
        }

        runUserCommands(commands);

        getRoot().getScene().getWindow().hide();
    }

    private List<Integer> getIndicesToMove() {
        List<Integer> list = new ArrayList<>();
        for (int integer : originalIndexToPushIndexMap.keySet()) {
            if (incompleteBlock.isIndexSelected(originalIndexToPushIndexMap.get(integer))) {
                list.add(integer);
            }
        }
        return list;
    }

    private List<Integer> getIndicesToDelete() {
        List<Integer> list = new ArrayList<>();
        for (int integer : originalIndexToDeleteIndexMap.keySet()) {
            if (completedBlock.isIndexSelected(originalIndexToDeleteIndexMap.get(integer))) {
                list.add(integer);
            }
        }
        return list;
    }
}
