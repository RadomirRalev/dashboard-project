package commands.actions.member;

import commands.actions.person.NameJoiner;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateMember implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public CreateMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        String teamToAddTo = parameters.get(0);
        if (checkIfTeamExists(teamToAddTo)) return String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamToAddTo);
        String personName = getPersonName();
        if (checkIfPersonExists(personName)) return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        MemberImpl member = addMemberToTeam(teamToAddTo, personName);
        addToMembersList(personName, member);
        return addToActivityHistory(teamToAddTo, personName);
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
        MemberImpl member = new MemberImpl(personName);
        team.addMember(member);
        return member;
    }

    private boolean checkIfPersonExists(String personName) {
        return !functionalsRepository.getPersons().containsKey(personName);
    }

    private String getPersonName() {
        String personName = reader.readLine();
        String[] personNameArr = personName.split(" ");
        personName = NameJoiner.joinerArr(personNameArr);
        return personName;
    }

    private boolean checkIfTeamExists(String teamToAddTo) {
        if (!functionalsRepository.getTeams().containsKey(teamToAddTo)) {
            return true;
        }
        writer.writeLine("Who should join this team?");
        return false;
    }
}
