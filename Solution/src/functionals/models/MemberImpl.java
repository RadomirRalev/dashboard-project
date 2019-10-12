package functionals.models;

import workitems.models.WorkItemsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List listWorkItems() {
        return workItems;
    }

    public String showActivity(String name) {
        return String.valueOf(getMembersActivity().get(name).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    @Override
    public String toString() {
        return String.format("MemberImpl name: %s\n" +
                        " Activity history: %s\n" +
                        " Work items:%s\n",
                getName(),
                showActivity(getName()),
                listWorkItems().toString()
        );
    }
}
