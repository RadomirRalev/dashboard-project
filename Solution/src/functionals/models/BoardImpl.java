package functionals.models;

import functionals.contracts.BoardAndPerson;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements BoardAndPerson {
    private String name;
    private List<String> activityHistory;
    private List workItems;

    public BoardImpl(String name, List activityHistory, List workItems) {
        setName(name);
        activityHistory = new ArrayList();
        workItems = new ArrayList();
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void addActivity() {
        activityHistory.add(); //String should come from engine
    }

    @Override
    public void removeActivity() { //String should come from engine
        activityHistory.remove();
    }

    @Override
    public List showActivity() {
        return activityHistory;
    }

    @Override
    public <T extends WorkItemsImpl> void addWorkItems(T workItem) {
        workItems.add(workItem);
    }

    @Override
    public <T extends WorkItemsImpl> void removeWorkItems(T workItem) {
        workItems.remove(workItem);
    }

    @Override
    public List listWorkItems() {
        return workItems;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Board name: %s\n" +
                        " Activity history: %s" +
                        " Work items:%s\n",
                getName(),
                showActivity(),
                listWorkItems().toString()
        );
    }
}
