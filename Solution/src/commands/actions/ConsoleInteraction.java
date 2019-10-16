package commands.actions;
import commands.actions.person.NameJoiner;
import core.contracts.Reader;
import core.providers.ConsoleReader;

import java.sql.SQLOutput;

import static commands.actions.CommandsConstants.*;

public abstract class ConsoleInteraction {
    private static Reader reader;

    protected ConsoleInteraction() {
        reader = new ConsoleReader();
    }

    protected static String asksWhich(String unit) {
        System.out.printf(WHICH, unit);
        return reader.readLine();
    }

    protected static String asksWhat() {
        System.out.printf(WHAT, "name");
        String[] personName = reader.readLine().split(" ");
        return NameJoiner.joinerArr(personName);
    }

    protected String asksAboutMemberOrBoard() {
        System.out.println(SHOW_ACTIVITY_HISTORY_QUESTION);
        return reader.readLine().toLowerCase();
    }
}
