package commands.actions.workitem.Change;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.FAILED_TO_PARSE_COMMAND_PARAMETERS;

public abstract class ChangeBase implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String changeableParameter;

    public ChangeBase(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        WorkItems workitem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        //checks if this board contains this workitem
        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        //checks if the work item name passed from the console matches the work item name in the repository
        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return changeCommand(workitemName, changeableParameter, workitem);
    }

    protected abstract String changeCommand(String workitemName, String changeableParameter, WorkItems workitem);

    protected void parseParameters(List<String> parameters) {
        try {
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
            changeableParameter = parameters.get(3);
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
