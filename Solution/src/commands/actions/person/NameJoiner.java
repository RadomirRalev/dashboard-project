package commands.actions.person;
import com.sun.xml.internal.ws.util.StringUtils;
import workitems.contracts.WorkItems;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class NameJoiner {
    private static AtomicInteger counter = new AtomicInteger(1);

    public static String joinerList(List<WorkItems> parameters) {
        StringJoiner str = new StringJoiner("===========\n");
        parameters.forEach(parameter -> str.add("Work item number: " + counter.getAndIncrement() + "\n" + parameter));
        return str.toString();
    }

    public static String joinerArr(String[] personNameArr) {
        StringJoiner str = new StringJoiner(" ");
        Arrays.stream(personNameArr).forEach(name -> {
            str.add(StringUtils.capitalize(name.toLowerCase()));
        });
        return str.toString();
    }
}