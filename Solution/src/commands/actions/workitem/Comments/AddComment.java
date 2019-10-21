package commands.actions.workitem.Comments;

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

public class AddComment implements Command {
    //TODO make it so you can see which person wrote a Comment
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;

    private final FunctionalsRepository functionalsRepository;

    private Reader reader;
    private Writer writer;
    private String workitemName;
    private int id;
    private String boardName;
    private String comment;

    public AddComment(FunctionalsRepository functionalsRepository){
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

        ValidationCommands.checkIfItemExists(functionalsRepository.getWorkItems(), id);

        WorkItems workItem = functionalsRepository.getWorkItems().get(id);
        Board board = functionalsRepository.getBoards().get(boardName);

        //checks if this board contains this workitem
        ValidationCommands.checkIfItemContainsAnother(board.listWorkItems(), workItem, workitemName, boardName);

        ValidationCommands.checkIfNamesMatch(workItem.getTitle(), workitemName);

        return addComment(workitemName, id, boardName, comment);
    }

    private String addComment(String workitemName, int id, String boardName, String comment){
        WorkItems workItem = functionalsRepository.getWorkItems().get(id);

        workItem.addComment(comment);

        return String.format(COMMENT_SUCCESSFULLY_ADDED_MSG, workitemName);
    }

    private void parseParameters(List<String> parameters) {
        try {
            workitemName = parameters.get(0);
            id = Integer.parseInt(parameters.get(1));
            boardName = parameters.get(2);
            writer.writeLine(String.format(ADD_COMMENT_MSG, workitemName, boardName));
            comment = reader.readLine();
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
