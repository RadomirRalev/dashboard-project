package commands.actions.workitem.ListWorkItems;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.BugAndStory;

import static commands.actions.CommandsConstants.*;

public class ListAllWorkItemsByStatusAndAsignee extends ListWorkItems implements Command {
    public ListAllWorkItemsByStatusAndAsignee(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getWriter().writeLine(CHOOSE_WORKITEM_ASSIGNEE);
        String filterTypeAsignee = getReader().readLine();

        getWriter().writeLine(CHOOSE_WORKITEM_STATUS_FILTER);
        String filterTypeStatus = getReader().readLine();

        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> !workitem.getItemType().equalsIgnoreCase(FEEDBACK))
                .map(workitem -> (BugAndStory) workitem)
                .filter(workitem -> workitem.getStatus().toString().equalsIgnoreCase(filterTypeStatus))
                .filter(workitem -> workitem.getAsignee().getName().equalsIgnoreCase(filterTypeAsignee))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
