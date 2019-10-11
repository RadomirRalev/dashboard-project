//package commands.actions.board;
//
//import commands.contracts.Command;
//import core.contracts.FunctionalsFactory;
//import core.contracts.FunctionalsRepository;
//import functionals.contracts.Board;
//import functionals.contracts.Team;
//
//import java.util.List;
//
//import static commands.actions.CommandsConstants.*;
//
//public class AddBoardToTeam implements Command {
//    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 2;
//
//    private final FunctionalsRepository functionalsRepository;
//    private final FunctionalsFactory functionalsFactory;
//
//    public AddBoardToTeam(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
//        this.functionalsRepository = functionalsRepository;
//        this.functionalsFactory = functionalsFactory;
//    }
//
//    @Override
//    public String execute(List<String> parameters) {
//        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
//            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
//        }
//
//        String teamToAddToName = parameters.get(0);
//        String boardToBeAddedName = parameters.get(1);
//        if (!functionalsRepository.getTeams().containsKey(teamToAddToName)) {
//            throw new IllegalArgumentException(String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamToAddToName));
//        }
//
//        if (!functionalsRepository.getBoards().containsKey(boardToBeAddedName)) {
//            throw new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST_ERROR_MSG, boardToBeAddedName));
//        }
//
//        Team team = functionalsRepository.getTeams().get(teamToAddToName);
//        Board board = functionalsRepository.getBoards().get(boardToBeAddedName);
//        team.addBoard(board);
//
//        return String.format(BOARD_ADDED_SUCCESS_MESSAGE, boardToBeAddedName, teamToAddToName);
//    }
//}
