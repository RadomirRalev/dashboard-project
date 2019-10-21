package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ShowActivityHistory extends ConsoleInteraction implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ShowActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        return choose(asksAboutMemberOrBoard());
    }

    private String choose(String str) {
        switch (str) {
            case "person":
                PersonActivityHistory personActivityHistory = new PersonActivityHistory(functionalsRepository);
                return personActivityHistory.execute();
            case "board":
                BoardActivityHistory boardActivityHistory = new BoardActivityHistory(functionalsRepository);
                return boardActivityHistory.execute();
            case "team":
                TeamActivityHistory teamActivityHistory = new TeamActivityHistory(functionalsRepository);
                return teamActivityHistory.execute();
            default :
                return HISTORY_UNAVAILABLE;
        }
    }

}


