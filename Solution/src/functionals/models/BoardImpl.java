package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.BoardAndPerson;
import workitems.contracts.WorkItems;
import workitems.models.WorkItemsImpl;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements BoardAndPerson, Board {
    //TODO implement the methods from the Board interface
    private String name;
    private List<String> activityHistory;
    private List workItems;

    public BoardImpl(String name) {
        setName(name);
        activityHistory = new ArrayList();
        workItems = new ArrayList();
    }

    private void setName(String name) {
        this.name = name;
    }


    @Override
    public void addActivity(String activity) {

    }

    @Override
    public List showActivity() {
        return activityHistory;
    }

    public <T extends WorkItems> void addWorkItems(T workItem) {
//        String workItemName = workItem.getTitle();
//        if (listWorkItems().contains(workItem.getTitle().equals(workItemName))) {
//            throw new IllegalArgumentException("You cannot add a WorkItem with the same name in the same board");
//        }
        workItems.add(workItem);
    }

    public <T extends WorkItems> void removeWorkItems(T workItem) {
        workItems.remove(workItem);
    }

    public List listWorkItems() {
        return new ArrayList(workItems);
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
                getName(),
                showActivity()));
        for (Object object : listWorkItems()) {
            str.append(String.format("%s\n", object));
        }
        return str.toString();
    }

    @Override
    public void addBug() {

    }

    @Override
    public void removeBug() {

    }

    @Override
    public void addStory() {

    }

    @Override
    public void removeStory() {

    }

    @Override
    public void addFeedback() {

    }

    @Override
    public void removeFeedback() {

    }
}
