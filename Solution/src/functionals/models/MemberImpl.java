package functionals.models;

import workitems.models.WorkItemsImpl;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl extends PersonImpl {
    private List workItems;

    public MemberImpl(String name) {
        super(name);
        workItems = new ArrayList();
    }

    public <T extends WorkItemsImpl> void addWorkItems(T workItem) {
        workItems.add(workItem);
    }

    public <T extends WorkItemsImpl> void removeWorkItems(T workItem) {
        workItems.remove(workItem);
    }

    public List listWorkItems() {
        return workItems;
    }

    @Override
    public String toString() {
        return String.format("MemberImpl name: %s\n" +
                        " Activity history: %s" +
                        " Work items:%s\n",
                getName(),
                listWorkItems().toString()
        );
    }
}
