package commands.actions.person;
import com.sun.xml.internal.ws.util.StringUtils;
import workitems.contracts.WorkItems;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class NameJoiner {
    public static String joinerList(List<WorkItems> parameters) {
        StringJoiner str = new StringJoiner("\n===========\n");
        for (int i = 0; i < parameters.size(); i++) {
            str.add("Work item number: " + (i+1) + "\n" + parameters.get(i).toString());
        }
        return str.toString();
    }

    public static String joinerArr(String[] personNameArr) {
        StringJoiner str = new StringJoiner(" ");
        Arrays.stream(personNameArr).forEach(name -> {
            name.toLowerCase();
            str.add(StringUtils.capitalize(name));
        });
        return str.toString();
    }
}