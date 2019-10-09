package commands.actions.team;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateTeam implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public CreateTeam(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String teamName = parameters.get(0);
        return createTeam(teamName);
    }

    private String createTeam(String name) {

        if (functionalsRepository.getTeams().containsKey(name)) {
            return String.format(TEAM_EXISTS_ERROR_MSG, name);
        }

        Team team = functionalsFactory.createTeam(name);
        functionalsRepository.addTeam(name, team);
        return String.format(TEAM_CREATED_MSG, name);
    }
}
