package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;

import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;
import static functionals.models.TeamsImpl.getTeamsActivity;

class TeamActivityHistory extends ConsoleInteraction {
    private final FunctionalsRepositoryImpl functionalsRepository;


    TeamActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    String execute() {
        String teamName = asksWhat("Team");
        teamName = ValidationCommands.checkIfTeamExists(teamName, functionalsRepository);
        if (isCancel(teamName)) {
            return TYPE_ANOTHER_COMMAND;
        }
        return showActivity(teamName);
    }

    private String showActivity(String teamActivityHistory) {
        return String.join("\n", getTeamsActivity().get(teamActivityHistory));
    }
}
