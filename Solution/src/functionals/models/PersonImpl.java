package functionals.models;
import functionals.contracts.Person;

import java.util.ArrayList;
import java.util.List;

import static commands.actions.activityhistory.ActHistory.getMembersActivity;

public class PersonImpl implements Person {
    private String name;
    private List<String> assignedWork;
    private ArrayList<String> activityHistory;



    public PersonImpl(String name) {
        setName(name);
        assignedWork = new ArrayList<>();
        activityHistory = new ArrayList();
    }

    @Override
    public void addActivity(String activity) {
        getMembersActivity().get(name).add(activity);
    }

    @Override
    public List showActivity() {
        return activityHistory;
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
