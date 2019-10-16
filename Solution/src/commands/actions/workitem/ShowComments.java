package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ShowComments implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;

    private final FunctionalsRepository functionalsRepository;

    private Reader reader;
    private Writer writer;
    private String workitemName;
    private int id;
    private String boardName;

    public ShowComments(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        WorkItems workItem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        //checks if this board contains this workitem
        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workItem, workitemName, boardName);

        ValidationCommands.checkIfNamesMatch(workItem.getTitle(), workitemName);

        return showComments(workitemName, id, boardName);
    }

    public String showComments(String workitemName, int id, String boardName){
        WorkItems workItem = functionalsRepository.getWorkItems().get(id);

        return workItem.showComments();
    }

    private void parseParameters(List<String> parameters) {
        try {
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
