package commands.actions.workitem.Change;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.FAILED_TO_PARSE_COMMAND_PARAMETERS;

public abstract class ChangeBase extends ConsoleInteraction implements Command {
    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String changeableParameter;
    private WorkItems workitem;

    public ChangeBase(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
        ConsoleInteraction.validateInput(parameters.size());

        parseParameters();

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        Board board = functionalsRepository.getBoards().get(boardName);

        //checks if this board contains this workitem
        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        //checks if the work item name passed from the console matches the work item name in the repository
        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return changeCommand(workitemName, changeableParameter, workitem);
    }

    protected abstract String changeCommand(String workitemName, String changeableParameter, WorkItems workitem);

    protected void parseParameters() {
        workitemName = asksWhat("workitem");
        id = asksWhatInt("id");
        id = ValidationCommands.checkIfWorkItemExists(id, functionalsRepository);
        boardName = asksWhat("board");
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);
        changeableParameter = asksWhatWillItBe(getChangeableParamterType());
        workitem = functionalsRepository.getWorkItems().get(id);
    }

    protected abstract String getChangeableParamterType();

    protected WorkItems getWorkItem() {
        return workitem;
    }

    public String getChangeableParameter() {
        return changeableParameter;
    }

    protected void setChangeableParameter(String changeableParameter) {
        this.changeableParameter = changeableParameter;
    }
}
