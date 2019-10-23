package commands.actions;

import commands.actions.person.NameJoiner;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import static commands.actions.CommandsConstants.*;
import static commands.actions.ValidationCommands.checkIfStringCanBeParsed;
import static commands.actions.ValidationCommands.trimInputAndCheckIfStringIsEmpty;

public class ConsoleInteraction {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private static Reader reader;
    protected String teamName;
    protected String boardName;
    protected String memberName;
    protected String personName;
<<<<<<< HEAD
=======
    private static Reader reader;
    private static Writer writer;
>>>>>>> 36d91a305174ba3513299e3d4203e4f3bc35b5bd
    protected int workToBeUnassigned;

    static {
        writer = new ConsoleWriter();
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
        writer.write(String.format(WHAT, unit));
        return reader.readLine();
    }

    protected static int asksWhatInt(String unit) {
        writer.write(String.format(WHICH, unit));
        String idString = reader.readLine();
        return checkIfStringCanBeParsed(idString);
    }

    protected static String asksAboutPersonName() {
        writer.write(String.format(WHAT, "Person"));
        String personName = reader.readLine();
        personName = trimInputAndCheckIfStringIsEmpty(personName);
        String[] name = personName.split(" ");
        return NameJoiner.joinerArr(name);
    }

    protected String asksAboutMemberOrBoard() {
        writer.writeLine(SHOW_ACTIVITY_HISTORY_QUESTION);
        return reader.readLine().toLowerCase();
    }

    protected static String asksWhich(String unit) {
        writer.write(String.format(WHICH, unit));
        return reader.readLine();
    }

    protected static String asksWhatWillItBe(String unit) {
        writer.writeLine(String.format(WHAT_WILL_IT_BE, unit));
        return reader.readLine();
    }

    protected boolean isCancel(String input) {
        return input.equalsIgnoreCase("cancel");
    }

    protected boolean isCancel(int id) {
        return id == 0;
    }

    protected void checkIfCommandCancelled(boolean isCancel) {
        if (isCancel) {
            throw new IllegalArgumentException(COMMAND_CANCELLED);
        }
    }

}
