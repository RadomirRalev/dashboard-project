package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ShowActivityHistory extends ConsoleInteraction implements Command {
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ShowActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute(List<String> parameters) throws Exception {
        return choose(asksAboutMemberOrBoard());
    }

    private String choose(String str) throws Exception {
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

