package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Severity;
import functionals.contracts.Board;
import workitems.contracts.Bug;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ChangeSeverity implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String newSeverity;

    public ChangeSeverity(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        WorkItems workItem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workItem, workitemName, boardName);

        ValidationCommands.checkIfNamesMatch(workItem.getTitle(), workitemName);

        return changeSeverity(workitemName, id, boardName, newSeverity);
    }

    private String changeSeverity(String workitemName, int id, String boardName, String newSeverity){
        Bug bug = (Bug) functionalsRepository.getWorkItems().get(id);

        bug.setSeverity(getSeverity(newSeverity));

        return String.format(SEVERITY_SUCCESSFULLY_CHANGED_MSG, workitemName, newSeverity);
    }

    private Severity getSeverity(String newSeverity){
        return Severity.valueOf(newSeverity.toUpperCase());
    }

    private void parseParameters(List<String> paramaters) {
        try {
            workitemName = paramaters.get(0);
            id = Integer.parseInt(paramaters.get(1));
            boardName = paramaters.get(2);
            newSeverity = paramaters.get(3);
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
