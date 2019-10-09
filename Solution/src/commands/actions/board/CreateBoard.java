package commands.actions.board;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Board;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateBoard implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public CreateBoard(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String boardName = parameters.get(0);
        return createBoard(boardName);
    }

    private String createBoard(String name) {

        if (functionalsRepository.getBoards().containsKey(name)) {
            return String.format(BOARD_EXISTS_ERROR_MSG, name);
        }

        Board board = functionalsFactory.createBoard(name);
        functionalsRepository.addBoard(name, board);
        return String.format(BOARD_CREATED_MSG, name);
    }
}
