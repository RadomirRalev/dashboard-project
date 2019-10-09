package commands.actions.person;

import java.util.List;
import java.util.StringJoiner;

public class NameJoiner {
    public static String joiner(List<String> parameters) {
        StringJoiner str = new StringJoiner(" ");
        for (String parameter : parameters) {
            str.add(parameter);
        }
        return str.toString();
    }
}
