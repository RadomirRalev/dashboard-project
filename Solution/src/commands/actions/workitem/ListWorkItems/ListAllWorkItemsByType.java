package commands.actions.workitem.ListWorkItems;

import commands.actions.ValidationCommands;
import commands.actions.workitem.ListWorkItems.ListWorkItems;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;

import static commands.actions.CommandsConstants.CHOOSE_WORKITEM_TYPE;

public class ListAllWorkItemsByType extends ListWorkItems implements Command {
    public ListAllWorkItemsByType(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getWriter().writeLine(CHOOSE_WORKITEM_TYPE);
        String filterType = getReader().readLine().toLowerCase();

        ValidationCommands.isFilterTypeValid(filterType);

        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> workitem.getItemType().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        ValidationCommands.isStringBuilderEmpty(stringBuilder);
        return stringBuilder.toString().trim();
    }
}
