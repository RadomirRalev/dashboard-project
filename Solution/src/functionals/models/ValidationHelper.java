package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.Team;

import java.util.EnumSet;

public class ValidationHelper {
    private static final String VALUE_OUT_OF_BOUNDS_ERROR = "Value is out of bounds!";
    private static final String ILLEGAL_ENUM_VALUE = "Illegal Enum value";
    private static final String BOARD_ALREADY_EXISTS_IN_TEAM = "Board %s already exists in team %s";

    public static void checkIfBoardExistsInTeam(Board board, Team team) {
        if (team.getBoards().contains(board)) {
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS_IN_TEAM, board.getName(), team.getName()));
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
