package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import workitems.contracts.Story;

import java.util.Comparator;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public abstract class ListWorkItems implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private FunctionalsRepository functionalsRepository;
    private Reader reader;
    private Writer writer;

    public ListWorkItems(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
//        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
//
//        String listCommandType = parameters.get(0).toLowerCase();
//
//        switch (listCommandType) {
//            case "all":
//                return listAllWorkItems();
//            case "type":
//                return listAllByType();
//            case "status":
//                return listAllByStatus();
//            case "size":
//                return listAllBySize();
//            default:
//                throw new IllegalArgumentException(String.format(INVALID_COMMAND, listCommandType));
//        }
        StringBuilder stringBuilder = new StringBuilder();

        return listCommand(stringBuilder);
    }

    protected FunctionalsRepository getFunctionalsRepository() {
        return functionalsRepository;
    }

    protected abstract String listCommand(StringBuilder stringBuilder);

    private String listAllByType() {
        System.out.println("Choose one of the following filters: Bug / Story / Feedback:");
        String filterType = reader.readLine().toLowerCase();

        ValidationCommands.isFilterTypeValid(filterType);

        StringBuilder stringBuilder = new StringBuilder();

        functionalsRepository.getWorkItems().values().stream()
                .filter(workitem -> workitem.getItemType().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }

    private String listAllByStatus() {
        writer.writeLine("Choose one of the following filters: Active / Fixed / NotDone / InProgress / Done / New " +
                "/ Unscheduled / Scheduled");
        String filterType = reader.readLine();

        StringBuilder stringBuilder = new StringBuilder();

        functionalsRepository.getWorkItems().values().stream()
                .filter(workitem -> workitem.getStatus().toString().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }

    private String listAllBySize() {
        StringBuilder stringBuilder = new StringBuilder();

        functionalsRepository.getWorkItems().values().stream()
                .filter(item -> item.getItemType().equalsIgnoreCase("Story"))
                .map(item -> (Story) item)
                .sorted(Comparator.comparingInt(x -> x.getSize().getWeight()))
                .forEach(item -> stringBuilder.append(item.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
