package commands.actions.board;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Board;
import functionals.contracts.Team;
import functionals.models.BoardImpl;
import java.util.ArrayList;
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
            throw new IllegalArgumentException(String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamName));
        }
        BoardImpl.getBoardsActivity().put(boardName, new ArrayList<>());

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
        String activity = String.format(BOARD_CREATED_MSG + "\n"
                        + BOARD_ADDED_SUCCESS_MESSAGE,
                boardName,
                boardName,
                teamName
        );
        BoardImpl.addActivity(activity, boardName);
        return String.format(BOARD_CREATED_MSG + "\n"
                        + BOARD_ADDED_SUCCESS_MESSAGE,
                boardName,
                boardName,
                teamName
        );
    }
}
