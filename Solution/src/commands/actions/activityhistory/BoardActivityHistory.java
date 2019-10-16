package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import java.util.stream.Collectors;
import static functionals.models.BoardImpl.getBoardsActivity;

public class BoardActivityHistory extends ConsoleInteraction {
    private String boardName;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public BoardActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    public String execute() throws Exception {
        setBoardName();
        ValidationCommands.checkIfBoardExists(getBoardName(), functionalsRepository);
        return showActivity(getBoardName());
    }

    private String showActivity(String boardActivityHistory) {
        return String.valueOf(getBoardsActivity().get(boardActivityHistory).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    private String getBoardName() {
        return boardName;
    }

    private void setBoardName() {
        this.boardName = asksWhich("board");
    }
}
