package commands.actions.member;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Team;
import functionals.models.MemberImpl;

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
        if (!functionalsRepository.getTeams().containsKey(teamToAddTo)) {
            return String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamToAddTo);
        }
        writer.writeLine("Who should join this team?");
        String personName = reader.readLine();
        if (!functionalsRepository.getPersons().containsKey(personName)) {
            return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        }
        Team team = functionalsRepository.getTeams().get(teamToAddTo);
        MemberImpl member = new MemberImpl(personName);
        team.addMember(member);
        functionalsRepository.getMembersList().add(personName + " (" + teamToAddTo + ")");
        return String.format(MEMBER_ADDED_MSG, personName, teamToAddTo);
    }
}
