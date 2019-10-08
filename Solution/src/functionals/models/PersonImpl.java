package functionals.models;

import functionals.contracts.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    private String name;
    private List<String> assignedWork;

    public PersonImpl(String name) {
        setName(name);
        assignedWork = new ArrayList<>();
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public List listAssignedWork() {
        return assignedWork;
    }

    @Override
    public void assignWork(String workToBeAdded) {
            assignedWork.add(workToBeAdded);
    }

    @Override
    public void unassignWork(int workToBeUnassigned) {
          assignedWork.remove(workToBeUnassigned);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Person name: %s\n" +
                        " Work assigned: %s",
                getName(),
                listAssignedWork()
        );
    }
}
