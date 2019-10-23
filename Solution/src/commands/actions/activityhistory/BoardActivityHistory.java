package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;
import static functionals.models.BoardImpl.getBoardsActivity;

class BoardActivityHistory extends ConsoleInteraction {
    private final FunctionalsRepositoryImpl functionalsRepository;

    BoardActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    String execute() {
        boardName = asksWhat("board");
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);
        if (isCancel(boardName)) {
            return TYPE_ANOTHER_COMMAND;
        }
        return showActivity(getBoardName());
    }

    private String showActivity(String boardActivityHistory) {
        return String.join("\n", getBoardsActivity().get(boardActivityHistory));
    }

    private String getBoardName() {
        return boardName;
    }
}
