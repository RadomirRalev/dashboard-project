package commands.actions.team;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;

import java.util.List;

import static commands.actions.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;
import static commands.actions.CommandsConstants.TEAM_DOES_NOT_EXIST_ERROR_MSG;

public class ShowTeamBoards extends ConsoleInteraction implements Command {
    private final FunctionalsRepository functionalsRepository;


    public ShowTeamBoards(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ConsoleInteraction.validateInput(parameters.size());
        setName("Team");
        ValidationCommands.checkIfTeamExists(getName(), functionalsRepository);
        return showTeamBoards();
    }

    private String showTeamBoards() {
        return functionalsRepository.getTeams().get(getName()).showBoards();
    }
}
