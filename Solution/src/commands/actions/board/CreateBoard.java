package commands.actions.board;

import commands.actions.ConsoleInteraction;
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

public class CreateBoard extends ConsoleInteraction implements Command {
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
        ConsoleInteraction.validateInput(parameters.size());

        parseParameters();

        return createBoard(boardName);
    }

    private String createBoard(String boardName) {
        BoardImpl.getBoardsActivity().put(boardName, new ArrayList<>());

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

    private void parseParameters() {
        teamName = asksWhat("Team");
        teamName = ValidationCommands.checkIfTeamExists(teamName, functionalsRepository);
        checkIfCommandCancelled(isCancel(teamName));

        boardName = asksWhat("Board");
        boardName = ValidationCommands.checkIfBoardAlreadyExistsInTeam(boardName, teamName, functionalsRepository);
        checkIfCommandCancelled(isCancel(boardName));
    }
}
