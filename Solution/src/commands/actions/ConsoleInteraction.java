package commands.actions;

import commands.actions.person.NameJoiner;
import core.contracts.Reader;
import core.providers.ConsoleReader;

import static commands.actions.CommandsConstants.*;

public class ConsoleInteraction {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private String name;
    private String personName;
    private static Reader reader;

    protected ConsoleInteraction() {
        reader = new ConsoleReader();
    }

    protected static void validateInput(int size) {
        if (size != correctArgumentsNumber()) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
    }

    protected String getName() {
        return name;
    }

    protected void setName(String unit) {
        this.name = asksWhat(unit);
    }

    protected String getPersonName() {
        return personName;
    }

    protected void setPersonName() {
        this.personName = asksAboutPersonName();
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
        String[] personName = reader.readLine().split(" ");
        return NameJoiner.joinerArr(personName);
    }

    protected String asksAboutMemberOrBoard() {
        System.out.println(SHOW_ACTIVITY_HISTORY_QUESTION);
        return reader.readLine().toLowerCase();
    }
}
