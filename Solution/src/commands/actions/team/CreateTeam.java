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
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        teamName = asksWhat("Team");
        teamName = ValidationCommands.checkNameOfNewTeam(teamName, functionalsRepository);
        if (isCancel(teamName)) return TYPE_ANOTHER_COMMAND;
        TeamsImpl.getTeamsActivity().put(teamName, new ArrayList<>());
        return createTeam(teamName);
    }

    private String createTeam(String name) {
        Team team = functionalsFactory.createTeam(name);
        functionalsRepository.addTeam(name, team);
        String activity = String.format(TEAM_CREATED_MSG, name);
        TeamsImpl.addActivity(activity, name);
        return String.format(TEAM_CREATED_MSG, name);
    }
}
