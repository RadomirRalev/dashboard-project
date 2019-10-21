package commands.actions.workitem.ListWorkItems;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;

import static commands.actions.CommandsConstants.CHOOSE_WORKITEM_STATUS_FILTER;

public class ListAllWorkItemsByStatus extends ListWorkItems implements Command {
    public ListAllWorkItemsByStatus(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getWriter().writeLine(CHOOSE_WORKITEM_STATUS_FILTER);
        String filterType = getReader().readLine();

        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> workitem.getStatus().toString().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
