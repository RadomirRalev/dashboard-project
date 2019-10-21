package commands.actions.workitem;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.BugAndStory;

import static commands.actions.CommandsConstants.*;

public class ListAllWorkItemsByAsignee extends ListWorkItems implements Command {

    public ListAllWorkItemsByAsignee(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getWriter().writeLine(CHOOSE_WORKITEM_ASSIGNEE);
        String filterType = getReader().readLine();

        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> workitem.getItemType().equalsIgnoreCase(BUG) ||
                        workitem.getItemType().equalsIgnoreCase(STORY))
                .map(workitem -> (BugAndStory) workitem)
                .filter(workitem -> workitem.getAsignee().getName().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
