package functionals.contracts;


import java.util.List;

public interface Person extends BoardAndPerson {

    void assignWork(String workToBeAdded);

    void unassignWork(int workToBeUnassigned);

    List listAssignedWork();
}
