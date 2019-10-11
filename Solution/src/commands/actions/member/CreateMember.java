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
        Person person = addMemberToTeam(teamToAddTo, personName);
        addToMembersList(personName, person);
        return addToActivityHistory(teamToAddTo, personName, person);
    }

    private void addToMembersList(String personName, Person person) {
        functionalsRepository.addMember(personName, person);
    }

    private String addToActivityHistory(String teamToAddTo, String personName, Person person) {
        String activity = String.format(MEMBER_ADDED_MSG, personName, teamToAddTo);
        person.addActivity(activity);
        return activity;
    }

    private Person addMemberToTeam(String teamToAddTo, String personName) {
        Team team = functionalsRepository.getTeams().get(teamToAddTo);
        Person person = functionalsRepository.getPersons().get(personName);
        team.addMember(person);
        return person;
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
