package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Size;
import functionals.contracts.Board;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ChangeSize implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String newSize;

    public ChangeSize(FunctionalsRepository functionalsRepository){
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        WorkItems workitem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workitem, workitemName, boardName);

        //checks if the Name passed from the console, actually matches the name of the Item with the passed ID
        ValidationCommands.checkIfNamesMatch(workitem.getTitle(), workitemName);

        return changeSize(workitemName, id, boardName, newSize);
    }

    private String changeSize(String workitemName, int id, String boardName, String newSize){
        Story story = (Story) functionalsRepository.getWorkItems().get(id);

        story.setSize(getSize(newSize));

        return String.format(SIZE_SUCCESSFULLY_CHANGED_MSG, workitemName, newSize);
    }

    private Size getSize(String newSize){
        return Size.valueOf(newSize.toUpperCase());
    }

    private void parseParameters(List<String> parameters) {
        try {
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
            newSize = parameters.get(3);
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
