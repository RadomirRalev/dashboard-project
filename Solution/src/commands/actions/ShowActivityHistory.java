package commands.actions;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import java.util.List;

import static commands.actions.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;
import static commands.actions.CommandsConstants.SHOW_ACTIVITY_HISTORY_QUESTION;

public class ShowActivityHistory implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ShowActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        checkNumberOfArguments(parameters);
        String activityHistoryOf = asksAboutMemberOrBoard();
        if (checkIfCommandIsCorrect(activityHistoryOf))
            return "Activity history is available only for members or boards!";
        if (activityHistoryOf.equalsIgnoreCase("member")) {
            MemberActivityHistory memberActivityHistory = new MemberActivityHistory(functionalsRepository);
            return memberActivityHistory.execute();
        }
        return null;
    }


    private boolean checkIfCommandIsCorrect(String activityHistoryOf) {
        return !activityHistoryOf.equalsIgnoreCase("member") &&
                !activityHistoryOf.equalsIgnoreCase("board");
    }

    private void checkNumberOfArguments(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
    }

    private String asksAboutMemberOrBoard() {
        writer.writeLine(SHOW_ACTIVITY_HISTORY_QUESTION);
        return reader.readLine();
    }
}
