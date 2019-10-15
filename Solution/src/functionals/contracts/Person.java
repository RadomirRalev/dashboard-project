package functionals.contracts;

import java.util.List;

public interface Person extends Functionals {

    void assignWork(String workToBeAdded);

    void unassignWork(int workToBeUnassigned);

    String listAssignedWork();

    List getAssignedWork();

    String showActivity(String name);
}
