package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;
import static functionals.models.BoardImpl.getBoardsActivity;

public class BoardActivityHistory extends ConsoleInteraction {
    private final FunctionalsRepositoryImpl functionalsRepository;

    public BoardActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    public String execute() {
        boardName = asksWhat("board");
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);
        if (isCancel(boardName)) {
            return TYPE_ANOTHER_COMMAND;
        }
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
        this.boardName = asksWhat("board");
    }
}
