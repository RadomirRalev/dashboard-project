package commands.actions;

import static commands.actions.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;

import java.util.List;

public class ValidationCommands {
    public static void validateInput(List<String> parameters, int expectedNumber) {
        if (parameters.size() != expectedNumber) {
            throw new IllegalArgumentException (INVALID_NUMBER_OF_ARGUMENTS);
        }
    }
}
