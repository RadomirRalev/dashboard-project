package commands.actions.board;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Board;
import functionals.contracts.Team;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateBoard implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;

    private String teamName;
    private String boardName;

    public CreateBoard(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters,CORRECT_NUMBER_OF_ARGUMENTS);

         boardName = parameters.get(0);
         teamName = parameters.get(1);

        if(!functionalsRepository.getTeams().containsKey(teamName)){
            throw new IllegalArgumentException(TEAM_DOES_NOT_EXIST_ERROR_MSG);
        }

        return createBoard(boardName);
    }

    private String createBoard(String boardName) {

        if (functionalsRepository.getBoards().containsKey(boardName)) {
            return String.format(BOARD_EXISTS_ERROR_MSG, boardName);
        }

        Team team = functionalsRepository.getTeams().get(teamName);
        Board board = functionalsFactory.createBoard(boardName);

        functionalsRepository.addBoard(boardName, board);
        team.addBoard(board);

        return String.format(BOARD_CREATED_MSG, boardName);
    }
}
