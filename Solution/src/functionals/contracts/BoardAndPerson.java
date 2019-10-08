package functionals.contracts;

import workitems.models.WorkItemsImpl;

import java.util.List;

public interface BoardAndPerson extends Functionals {

    void addActivity();

    void removeActivity();

    List showActivity();

    <T extends WorkItemsImpl> void addWorkItems(T workItem);

    <T extends WorkItemsImpl> void removeWorkItems(T workItem);

    List listWorkItems();

}
