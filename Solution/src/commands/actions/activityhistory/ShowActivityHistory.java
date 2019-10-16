package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import java.util.List;
import static commands.actions.CommandsConstants.*;

public class ShowActivityHistory implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ShowActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
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

    private String asksAboutMemberOrBoard() {
        writer.writeLine(SHOW_ACTIVITY_HISTORY_QUESTION);
        return reader.readLine().toLowerCase();
    }
}

