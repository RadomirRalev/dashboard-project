package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.Feedback;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.FAILED_TO_PARSE_COMMAND_PARAMETERS;
import static commands.actions.CommandsConstants.RATING_SUCCESSFULLY_CHANGED_MSG;

public class ChangeRating implements Command {
    //TODO make a validation for the casting of feedback
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private int newRating;

    public ChangeRating(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters)  {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        WorkItems workitem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        //checks if the Name passed from the console, actually matches the teamName of the Item with the passed ID
        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return changeRating(workitemName, id, boardName, newRating);
    }

    private String changeRating(String workitemName, int id, String boardName, int newRating) {
        Feedback feedback = (Feedback) functionalsRepository.getWorkItems().get(id);

        feedback.setRating(newRating);

        return String.format(RATING_SUCCESSFULLY_CHANGED_MSG, workitemName, newRating);
    }

    private void parseParameters(List<String> parameters) {
        try {
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
            newRating = Integer.parseInt(parameters.get(3));
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
