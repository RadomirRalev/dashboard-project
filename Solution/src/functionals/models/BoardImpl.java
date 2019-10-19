package functionals.models;

import functionals.contracts.Board;
import workitems.contracts.WorkItems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardImpl implements Board {
    private static final Map<String, ArrayList<String>> boardsActivity = new HashMap<>();
    private String name;
    private List<String> activityHistory;
    private List<WorkItems> workItems;

    public BoardImpl(String name) {
        setName(name);
        activityHistory = new ArrayList<>();
        workItems = new ArrayList<>();
    }

    public static Map<String, ArrayList<String>> getBoardsActivity() {
        return boardsActivity;
    }

    public static void addActivity(String activity, String name) {
        getBoardsActivity().get(name).add(activity);
    }

    private void setName(String name) {
        this.name = name;
    }

    public <T extends WorkItems> void addWorkItems(T workItem) {
        String workItemName = workItem.getTitle();
        if (listWorkItems().contains(workItem.getTitle().equals(workItemName))) {
            throw new IllegalArgumentException("You cannot add a WorkItem with the same teamName in the same board");
        }
        workItems.add(workItem);
    }

    public <T extends WorkItems> void removeWorkItems(T workItem) {
        workItems.remove(workItem);
    }

    public List listWorkItems() {
        return new ArrayList<>(workItems);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Board teamName: %s\n" +
                        "Activity history: %s\n" +
                        "Work items:\n",
                getName(), getBoardsActivity()));
        for (Object object : listWorkItems()) {
            str.append(String.format("%s\n", object));
        }
        return str.toString();
    }
}
