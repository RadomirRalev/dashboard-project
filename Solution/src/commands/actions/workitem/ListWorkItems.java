package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ListWorkItems implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;

    private FunctionalsRepository functionalsRepository;
    private String listCommandType;
    private Reader reader;
    private Writer writer;

    public ListWorkItems(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        listCommandType = parameters.get(0).toLowerCase();

        switch (listCommandType) {
            case "all":
                return listAllWorkItems();
            case "type":
                return listAllByType();
            case "status":
                return listAllByStatus();
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, listCommandType));
        }
    }

    private String listAllWorkItems() {
        StringBuilder stringBuilder = new StringBuilder();

        functionalsRepository.getWorkItems().entrySet().stream()
//                .sorted((workitem1, workitem2) ->
//                        workitem1.getValue().getTitle().compareToIgnoreCase(workitem2.getValue().getTitle()))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }

    private String listAllByType() {
        writer.writeLine("Choose one of the following filters: Bug / Story / Feedback");
        String filterType = reader.readLine();

        StringBuilder stringBuilder = new StringBuilder();

        functionalsRepository.getWorkItems().entrySet().stream()
                .filter(workitem -> workitem.getValue().getItemType().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }

    private String listAllByStatus() {
        writer.writeLine("Choose one of the following filters: Active / Fixed / NotDone / InProgress / Done / New " +
                "/ Unscheduled / Scheduled");
        String filterType = reader.readLine();

        StringBuilder stringBuilder = new StringBuilder();

        functionalsRepository.getWorkItems().entrySet().stream()
                .filter(workitem -> workitem.getValue().getStatus().toString().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
