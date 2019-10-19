package commands.actions.team;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ShowTeamBoards extends ConsoleInteraction implements Command {
    private final FunctionalsRepository functionalsRepository;


    public ShowTeamBoards(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ConsoleInteraction.validateInput(parameters.size());
        teamName = asksWhat("Team");
        teamName = ValidationCommands.checkIfTeamExists(teamName, functionalsRepository);
        if (isCancel(teamName)) return TYPE_ANOTHER_COMMAND;
        return showTeamBoards();
    }

    private String showTeamBoards() {
        return functionalsRepository.getTeams().get(teamName).showBoards();
    }
}

