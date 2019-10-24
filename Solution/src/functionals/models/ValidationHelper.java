package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.Team;
import workitems.contracts.WorkItems;

import java.util.EnumSet;

public class ValidationHelper {
    private static final String VALUE_OUT_OF_BOUNDS_ERROR = "Value is out of bounds!";
    private static final String ILLEGAL_ENUM_VALUE = "Illegal Enum value";
    private static final String BOARD_ALREADY_EXISTS_IN_TEAM = "Board %s already exists in team %s";
    private static final String WORKITEM_ALREADY_EXISTS_IN_BOARD = "Board %s already contains workitem %s";
    private static final String WORKITEM_DOESNT_EXISTS_IN_BOARD = "Board %s doesn't contain workitem %s";

    public static void checkIfBoardExistsInTeam(Board board, Team team) {
        if (team.getBoards().contains(board)) {
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS_IN_TEAM, board.getName(), team.getName()));
        }
    }

    public static void checkIfWorkItemDoesntExistsInBoard(Board board, WorkItems workitem) {
        if (!board.listWorkItems().contains(workitem)) {
            throw new IllegalArgumentException(String.format(WORKITEM_DOESNT_EXISTS_IN_BOARD, board.getName(), workitem.getTitle()));
        }
    }


    public static void checkStringLengthInBounds(String title, int minNumber, int maxNumber) {
        if (title.length() < minNumber || title.length() > maxNumber) {
            throw new IllegalArgumentException(VALUE_OUT_OF_BOUNDS_ERROR);
        }
    }

    public static void checkNumberInBounds(int number, int minValue, int maxValue) {
        if (number < minValue || number > maxValue) {
            throw new IllegalArgumentException(VALUE_OUT_OF_BOUNDS_ERROR);
        }
    }

    public static <T extends Enum<T>> void checkIfEnumValueIsValid(Enum<T> enumValue
            , EnumSet<T> enumSet) {
        if (!enumSet.contains(enumValue)) {
            throw new IllegalArgumentException(ILLEGAL_ENUM_VALUE);
        }
    }

}
