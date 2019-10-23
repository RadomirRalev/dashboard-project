package commands.actions.workitem;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class RemoveWorkItem extends ConsoleInteraction implements Command {
    private final FunctionalsRepository functionalsRepository;

    private int id;
    private String workitemName;
    private String boardName;

    public RemoveWorkItem(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());

        parseParameters();

        WorkItems workitem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return removeWorkItem(workitem, board);
    }

    private String removeWorkItem(WorkItems workitem, Board board) {
        board.removeWorkItems(workitem);

        functionalsRepository.removeWorkItem(id);

        return String.format(WORK_ITEM_SUCCESS_REMOVAL_MESSAGE, workitemName, id);
    }

    private void parseParameters() {
        workitemName = asksWhat("workitem");
        id = asksWhatInt("id");
        id = ValidationCommands.checkIfWorkItemExists(id, functionalsRepository);
        checkIfCommandCancelled(isCancel(id));
        boardName = asksWhat("board");
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);
        checkIfCommandCancelled(isCancel(boardName));
    }
}
