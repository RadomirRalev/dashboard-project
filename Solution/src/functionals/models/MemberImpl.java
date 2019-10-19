package functionals.models;
import core.contracts.FunctionalsRepository;
import workitems.models.WorkItemsImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MemberImpl extends PersonImpl {
    private final FunctionalsRepository functionalsRepository;
    private List workItems;

    public MemberImpl(String name, FunctionalsRepository functionalsRepository) {
        super(name);
        this.functionalsRepository = functionalsRepository;
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

    public ArrayList<String> getTheTeamsOfTheMember() {
        return setTeamsOfTheMember();
    }


    private ArrayList<String> setTeamsOfTheMember() {
        ArrayList<String> teamsOfMember = new ArrayList<>();
        functionalsRepository.getTeams().forEach((k, v) -> {
            if (functionalsRepository.getTeams().get(k).showTeamMembers().contains(this)) {
                teamsOfMember.add(functionalsRepository.getTeams().get(k).getName());
            }
        });
        return teamsOfMember;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\n" +
                        "Member of team(s): %s\n" +
                        "Activity history:\n %s\n" +
                        "Work items:%s\n" +
                        "=============",
                getName(),
                getTheTeamsOfTheMember().toString(). replace("[", "").replace("]", ""),
                showActivity(getName()),
                listWorkItems().toString()
        );
    }
}
