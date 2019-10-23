package functionals.models;

import core.contracts.FunctionalsRepository;
import java.util.ArrayList;

public class MemberImpl extends PersonImpl {
    private final FunctionalsRepository functionalsRepository;

    public MemberImpl(String name, FunctionalsRepository functionalsRepository) {
        super(name);
        this.functionalsRepository = functionalsRepository;
    }

    public String showActivity(String name) {
        return String.join("\n", getMembersActivity().get(name));
    }

    public ArrayList<String> getTheTeamsOfTheMember() {
        return setTeamsOfTheMember();
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

    private ArrayList<String> setTeamsOfTheMember() {
        ArrayList<String> teamsOfMember = new ArrayList<>();
        functionalsRepository.getTeams().forEach((k, v) -> {
            if (functionalsRepository.getTeams().get(k).showTeamMembers().contains(this)) {
                teamsOfMember.add(functionalsRepository.getTeams().get(k).getName());
            }
        });
        return teamsOfMember;
    }


}
