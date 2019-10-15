package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import java.util.stream.Collectors;
import static commands.actions.CommandsConstants.*;
import static functionals.models.BoardImpl.getBoardsActivity;

public class BoardActivityHistory {
    private Reader reader;
    private Writer writer;
    private String boardName;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public BoardActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute() throws Exception {
        writer.writeLine(WHICH_BOARD);
        boardName = asksWhichBoard();
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);
        return showActivity(boardName);
    }

    private String showActivity(String boardActivityHistory) {
        return String.valueOf(getBoardsActivity().get(boardActivityHistory).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    private String asksWhichBoard() {
        return boardName = reader.readLine();
    }
}
