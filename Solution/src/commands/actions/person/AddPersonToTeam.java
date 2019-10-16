package commands.actions.person;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Team;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class AddPersonToTeam extends ConsoleInteraction implements Command {

    private final FunctionalsRepository functionalsRepository;

    public AddPersonToTeam(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ConsoleInteraction.validateInput(parameters.size());
        setName("Team");
        ValidationCommands.checkIfTeamExists(getName(), functionalsRepository);
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        MemberImpl member = addMemberToTeam(getName(), getPersonName());
        addToMembersList(getPersonName(), member);
        return addToActivityHistory(getName(), getPersonName());
    }

    private void addToMembersList(String personName, MemberImpl member) {
        functionalsRepository.addMember(personName, member);
    }

    private String addToActivityHistory(String teamToAddTo, String personName) {
        String activity = String.format(MEMBER_ADDED_MSG, personName, teamToAddTo);
        PersonImpl.addActivity(activity, personName);
        TeamsImpl.addActivity(activity, teamToAddTo);
        return activity;
    }

    private MemberImpl addMemberToTeam(String teamToAddTo, String personName) {
        Team team = functionalsRepository.getTeams().get(teamToAddTo);
        MemberImpl member = new MemberImpl(personName, functionalsRepository);
        team.addMember(member);
        return member;
    }
}
