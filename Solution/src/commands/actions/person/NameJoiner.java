package commands.actions.person;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.List;
import java.util.StringJoiner;

public class NameJoiner {
    public static String joinerList(List<String> parameters) {
        StringJoiner str = new StringJoiner(" ");
        for (String parameter : parameters) {
            parameter = parameter.toLowerCase();
            str.add(StringUtils.capitalize(parameter));
        }
        return str.toString();
    }
    public static String joinerArr(String[] personNameArr) {
        StringJoiner str = new StringJoiner(" ");
        for (String parameter : personNameArr) {
            parameter = parameter.toLowerCase();
            str.add(StringUtils.capitalize(parameter));
        }
        return str.toString();
    }
}
