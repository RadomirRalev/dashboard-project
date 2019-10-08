package commands.actions.member;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.MemberImpl;

import java.lang.reflect.Member;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateMember implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public CreateMember(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String teamToAddTo = parameters.get(0);
        writer.writeLine("Who should join this team?");
        String personName = reader.readLine();
        if (!functionalsRepository.getPersons().containsKey(personName)) {
            return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        }
        if (!functionalsRepository.getTeams().containsKey(teamToAddTo)) {
            return String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamToAddTo);
        }
        Team team = functionalsRepository.getTeams().get(teamToAddTo);
        MemberImpl member = new MemberImpl(personName);
        team.addMember(member);
        return String.format(MEMBER_ADDED_MSG, personName, teamToAddTo);
    }
}
