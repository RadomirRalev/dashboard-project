package commands.actions.person;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class NameJoiner {
    public static String joinerList(List<String> parameters) {
        StringJoiner str = new StringJoiner(" ");
        parameters.forEach(e -> {
            e.toLowerCase();
            str.add(StringUtils.capitalize(e));
        });
        return str.toString();
    }

    public static String joinerArr(String[] personNameArr) {
        StringJoiner str = new StringJoiner(" ");
        Arrays.stream(personNameArr).forEach(e -> {
            e.toLowerCase();
            str.add(StringUtils.capitalize(e));
        });
        return str.toString();
    }
}
