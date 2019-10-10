package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.BoardAndPerson;
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

    public <T extends WorkItemsImpl> void addWorkItems(T workItem) {
        workItems.add(workItem);
    }

    public <T extends WorkItemsImpl> void removeWorkItems(T workItem) {
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
        return String.format("Board name: %s\n" +
                        " Activity history: %s" +
                        " Work items:%s",
                getName(),
                showActivity(),
                listWorkItems().toString()
        );
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
