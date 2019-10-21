package commands.actions.workitem;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;

public class ListAllWorkItems extends ListWorkItems implements Command {
    public ListAllWorkItems(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }

}
