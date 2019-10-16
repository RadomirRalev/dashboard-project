package commands.actions.team;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Team;
import functionals.models.TeamsImpl;

import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateTeam extends ConsoleInteraction implements Command {
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public CreateTeam(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ConsoleInteraction.validateInput(parameters.size());
        setName("Team");
        ValidationCommands.checkNameOfNewTeam(getName(), functionalsRepository);
        TeamsImpl.getTeamsActivity().put(getName(), new ArrayList<>());
        return createTeam(getName());
    }

    private String createTeam(String name) {
        Team team = functionalsFactory.createTeam(name);
        functionalsRepository.addTeam(name, team);
        String activity = String.format(TEAM_CREATED_MSG, name);
        TeamsImpl.addActivity(activity, name);
        return String.format(TEAM_CREATED_MSG, name);
    }
}
