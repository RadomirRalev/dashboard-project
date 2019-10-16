package commands.actions.team;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static commands.actions.CommandsConstants.*;

public class ListTeams extends ConsoleInteraction implements Command {
    private FunctionalsRepositoryImpl functionalsRepository;

    public ListTeams(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        return listTeams();
    }

    private String listTeams() {
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
