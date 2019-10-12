package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Priority;
import functionals.contracts.Board;
import workitems.contracts.BugAndStory;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.FAILED_TO_PARSE_COMMAND_PARAMETERS;
import static commands.actions.CommandsConstants.PRIORITY_SUCCESSFULLY_CHANGED_MSG;

public class ChangePriority implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String newPriority;

    public ChangePriority(FunctionalsRepository functionalsRepository){
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        WorkItems workItem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        //checks if this board contains this workitem
        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workItem, workitemName, boardName);

        ValidationCommands.checkIfNamesMatch(workItem.getTitle(), workitemName);

        return changePriority(workitemName, id, boardName, newPriority);
    }

    private String changePriority(String workitemName, int id, String boardName, String newPriority){
        BugAndStory bugAndStory = (BugAndStory) functionalsRepository.getWorkItems().get(id);

        bugAndStory.setPriority(getPriority(newPriority));

        return String.format(PRIORITY_SUCCESSFULLY_CHANGED_MSG, workitemName, newPriority);
    }

    private void parseParameters(List<String> parameters){
        try{
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
            newPriority = parameters.get(3);
        } catch (Exception e){
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }

    private Priority getPriority(String newPriority){
        return Priority.valueOf(newPriority.toUpperCase());
    }
}
