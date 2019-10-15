package commands.actions.member;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Team;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class AddPersonToTeam implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private String teamName;
    private String personName;

    public AddPersonToTeam(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setTeamName();
        ValidationCommands.checkIfTeamExists(getTeamName(), functionalsRepository);
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        MemberImpl member = addMemberToTeam(getTeamName(), getPersonName());
        addToMembersList(getPersonName(), member);
        return addToActivityHistory(getTeamName(), getPersonName());
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

    private String getTeamName() {
        return teamName;
    }

    private void setTeamName() {
        this.teamName = ValidationCommands.asksWhichTeam();
    }

    private String getPersonName() {
        return personName;
    }

    private void setPersonName() {
        this.personName = ValidationCommands.asksWhichPerson();
    }
}
