package commands.actions;

import commands.actions.person.NameJoiner;
import commands.contracts.Command;import core.contracts.FunctionalsRepository;import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import java.util.Map;import java.util.List;import static commands.actions.CommandsConstants.*;
import static commands.actions.ValidationCommands.trimInputAndCheckIfStringIsEmpty;

public class ConsoleInteraction {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    protected String teamName;
    protected String boardName;
    protected String memberName;
    protected String personName;
    private static Reader reader;
    protected int workToBeUnassigned;

    protected ConsoleInteraction() {
        reader = new ConsoleReader();
    }

    protected static void validateInput(int size) {
        if (size != correctArgumentsNumber()) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
    }

    private static int correctArgumentsNumber() {
        return CORRECT_NUMBER_OF_ARGUMENTS;
    }

    protected static String asksWhat(String unit) {
        System.out.printf(WHAT, unit);
        return reader.readLine();
    }

    protected static String asksAboutPersonName() {
        System.out.printf(WHAT, "Person");
        String personName = reader.readLine();
        personName = trimInputAndCheckIfStringIsEmpty(personName);
        String[] name = personName.split(" ");
        return NameJoiner.joinerArr(name);
    }

    protected String asksAboutMemberOrBoard() {
        System.out.println(SHOW_ACTIVITY_HISTORY_QUESTION);
        return reader.readLine().toLowerCase();
    }

    protected static String asksWhich(String unit) {
        System.out.printf(WHICH, unit);
        return reader.readLine();
    }

    protected static String asksWhatWillItBe(String unit) {
        System.out.println(String.format(WHAT_WILL_IT_BE, unit));
        return reader.readLine();
    }

    protected boolean isCancel(String input) {
        return input.equalsIgnoreCase("cancel");
    }
    
}
