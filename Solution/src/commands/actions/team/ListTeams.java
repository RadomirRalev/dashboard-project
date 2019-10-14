package commands.actions.team;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static commands.actions.CommandsConstants.*;

public class ListTeams implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private FunctionalsRepositoryImpl functionalsRepository;


    public ListTeams(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        StringJoiner str = new StringJoiner(", ");
        Set<String> keys = functionalsRepository.getTeams().keySet();
        for (String k : keys) {
            str.add(k);
        }
        String teamsList = str.toString(); //in alphabetical order
        return listTeams(teamsList);
    }

    private String listTeams(String teamsList) {

        if (functionalsRepository.getTeams().size() == 0) {
            return TEAMSLIST_IS_EMPTY;
        }
        return String.format(TEAMSLIST_INCLUDES, teamsList);
    }
}
