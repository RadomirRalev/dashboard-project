package functionals.models;
import functionals.contracts.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonImpl implements Person {
    private static final Map<String, ArrayList<String>> membersActivity = new HashMap<>();
    private String name;
    private List<String> assignedWork;

    public PersonImpl(String name) {
        setName(name);
        assignedWork = new ArrayList<>();
    }

    public static Map<String, ArrayList<String>> getMembersActivity() {
        return membersActivity;
    }

    public static void addActivity(String activity, String name) {
        getMembersActivity().get(name).add(activity);
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
