package functionals.models;

import functionals.contracts.BoardAndPerson;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl extends PersonImpl implements BoardAndPerson {
    private List<String> activityHistory;
    private List workItems;

    public MemberImpl(String name, List activityHistory, List workItems) {
        super(name);
        activityHistory = new ArrayList();
        workItems = new ArrayList();
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
    public String toString() {
        return String.format("MemberImpl name: %s\n" +
                        " Activity history: %s" +
                        " Work items:%s\n",
                getName(),
                showActivity(),
                listWorkItems().toString()
        );
    }
}
