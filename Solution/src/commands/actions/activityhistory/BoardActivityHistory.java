package commands.actions.activityhistory;

import commands.actions.person.NameJoiner;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Board;
import functionals.contracts.Person;

import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.*;

public class BoardActivityHistory {
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public BoardActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute() {
        writer.writeLine(WHICH_BOARD);
        String boardActivityHistory = asksWhichBoard();
        while (!checkIfBoardExists(boardActivityHistory)) {
            System.out.printf(BOARD_DOES_NOT_EXIST_MSG, boardActivityHistory);
            boardActivityHistory = asksWhichBoard();
            if (boardActivityHistory.equalsIgnoreCase("cancel")) {
                return TYPE_ANOTHER_COMMAND;
            }
        }
        return showActivity(boardActivityHistory);
    }

    private String showActivity(String boardActivityHistory) {
        Board board = functionalsRepository.getBoards().get(boardActivityHistory);
        return String.valueOf(board.showActivity().stream()
                .map( n -> n.toString() )
                .collect( Collectors.joining( " " ) ));
    }

    private String asksWhichBoard() {
        String[] boardActivityHistory = reader.readLine().split(" ");
        return NameJoiner.joinerArr(boardActivityHistory);
    }

    private boolean checkIfBoardExists(String boardActivityHistory) {
        return functionalsRepository.getMembers().containsKey(boardActivityHistory);
    }

}
