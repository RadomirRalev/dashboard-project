package functionals.models;

import functionals.contracts.Person;
import workitems.contracts.WorkItems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonImpl implements Person {
    private static final Map<String, ArrayList<String>> membersActivity = new HashMap<>();
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private String name;
    private List<WorkItems> workItems;

    public PersonImpl(String name) {
        setName(name);
        workItems = new ArrayList<>();
    }

    public static Map<String, ArrayList<String>> getMembersActivity() {
        return membersActivity;
    }

    public static void addActivity(String activity, String name) {
        getMembersActivity().get(name).add(activity);
    }

    @Override
    public String showActivity(String name) {
        return String.join("\n", getMembersActivity().get(name));
    }

    @Override
    public List getAssignedWork() {
        return workItems;
    }

    @Override
    public List listWorkItems() {
        return new ArrayList<>(workItems);
    }

    @Override
    public <T extends WorkItems> void addWorkItems(T workItem) {
        String workItemName = workItem.getTitle();
        if (listWorkItems().contains(workItem.getTitle().equals(workItemName))) {
            throw new IllegalArgumentException("You cannot add a WorkItem with the same name in the same board");
        }
        workItems.add(workItem);
    }

    @Override
    public void unassignWork(int workToBeUnassigned) {
        workItems.remove(workToBeUnassigned);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Board name: %s\n" +
                        "Activity history: %s\n" +
                        "Work items:\n",
                getName(), getMembersActivity()));
        for (Object object : listWorkItems()) {
            str.append(String.format("%s\n", object));
        }
        return str.toString();
    }

    private void setName(String name) {
        ValidationHelper.checkStringLengthInBounds(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH);
        this.name = name;
    }
}
