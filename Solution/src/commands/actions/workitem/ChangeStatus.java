package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Status;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ChangeStatus implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String newStatus;

    public ChangeStatus(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        WorkItems workitem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        //checks if the Name passed from the console, actually matches the name of the Item with the passed ID
        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return changeStatus(boardName, workitemName, id, newStatus);
    }

    private String changeStatus(String boardName, String workitemName, int id, String newStatus) {
        WorkItems workItem = functionalsRepository.getWorkItems().get(id);

        workItem.setStatus(getStatus(newStatus));

        return String.format(STATUS_SUCCESSFULLY_CHANGED_MSG, workitemName, newStatus);
    }

    private void parseParameters(List<String> parameters) {
        try {
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
            newStatus = parameters.get(3);
        } catch (Exception E) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }

    private Status getStatus(String newStatus) {
        return Status.valueOf(newStatus.toUpperCase());
    }
}

