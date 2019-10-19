package functionals.contracts;

import workitems.contracts.WorkItems;

import java.util.List;

public interface Person extends Functionals {

    <T extends WorkItems> void addWorkItems(T workItem);

    void unassignWork(int workToBeUnassigned);

    List listWorkItems();

    List getAssignedWork();

    String showActivity(String name);
}
