package commands.actions.team;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;

import java.util.List;

import static commands.actions.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;
import static commands.actions.CommandsConstants.TEAM_DOES_NOT_EXIST_ERROR_MSG;

public class ShowTeamBoards implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

    private final FunctionalsRepository functionalsRepository;


    public ShowTeamBoards(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        String teamToShowBoards = parameters.get(0);
        return showTeamBoards(teamToShowBoards);
    }

    private String showTeamBoards(String teamName) {
        if (!functionalsRepository.getTeams().containsKey(teamName)) {
            return String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamName);
        }

        return functionalsRepository.getTeams().get(teamName).showBoards();
    }
}
