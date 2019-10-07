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

    private void setAssignedWork(List<String> assignedWork) {
        this.assignedWork = assignedWork;
    }

    @Override
    public List listAssignedWork() {
        return assignedWork;     //String should come from engine
    }

    @Override
    public void assignWork() {
        assignedWork.add();     //String should come from engine
    }

    @Override
    public void unassignWork() {
        assignedWork.remove();  //String should come from engine
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
