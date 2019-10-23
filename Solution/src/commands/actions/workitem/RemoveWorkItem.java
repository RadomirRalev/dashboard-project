package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class RemoveWorkItem implements Command {
    //TODO make command interact with consoles
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;

    private final FunctionalsRepository functionalsRepository;

    private int id;
    private String workItemName;
    private String boardName;

    public RemoveWorkItem(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        if (!functionalsRepository.getBoards().containsKey(boardName)) {
            throw new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST_ERROR_MSG, boardName));
        }

        if (!functionalsRepository.getWorkItems().containsKey(id)) {
            throw new IllegalArgumentException(String.format(WORKITEM_DOES_NOT_EXIST_ERROR_MSG, workItemName, id));
        }

        WorkItems workitem = functionalsRepository.getWorkItems().get(id);

        if (!functionalsRepository.getBoards().get(boardName).listWorkItems().contains(workitem)) {
            throw new IllegalArgumentException(String.format(WORKITEM_DOES_NOT_EXIST_IN_BOARD_MSG, workItemName, id,
                    boardName));
        }

        if(!workitem.getTitle().equals(workItemName)){
            throw new IllegalArgumentException(WORKITEM_ID_DOES_NOT_MATCH_NAME_MSG);
        }

        return removeWorkItem();
    }

    private String removeWorkItem() {
        WorkItems workItem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        board.removeWorkItems(workItem);

        functionalsRepository.removeWorkItem(id);

        return String.format(WORK_ITEM_SUCCESS_REMOVAL_MESSAGE, workItemName, id);
    }

    private void parseParameters(List<String> parameters) {
        try {
            boardName = parameters.get(0);
            workItemName = parameters.get(1);
            id = Integer.parseInt(parameters.get(2));
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
