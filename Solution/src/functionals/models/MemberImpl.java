package functionals.models;

import functionals.contracts.BoardAndPerson;
import workitems.models.WorkItemsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberImpl extends PersonImpl implements BoardAndPerson {
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
