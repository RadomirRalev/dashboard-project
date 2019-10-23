package commands.actions.workitem.Comments;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

public abstract class CommentBase extends ConsoleInteraction implements Command {
    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private WorkItems workitem;

    public CommentBase(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
        ConsoleInteraction.validateInput(parameters.size());

        parseParameters();

        Board board = functionalsRepository.getBoards().get(boardName);

        //checks if this board contains this workitem
        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return commentCommand(workitemName, id, workitem);
    }

    protected abstract String commentCommand(String workitemName, int id, WorkItems workitem);

    protected void parseParameters() {
        workitemName = asksWhat("workitem");
        id = asksWhatInt("id");
        id = ValidationCommands.checkIfWorkItemExists(id, functionalsRepository);
        boardName = asksWhat("board");
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);
        workitem = functionalsRepository.getWorkItems().get(id);
    }
}
