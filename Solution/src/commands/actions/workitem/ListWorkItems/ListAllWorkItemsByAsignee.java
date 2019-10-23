package commands.actions.workitem.ListWorkItems;

import commands.actions.ValidationCommands;
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
                .filter(workitem -> !workitem.getItemType().equalsIgnoreCase(FEEDBACK))
                .map(workitem -> (BugAndStory) workitem)
                .filter(workitem -> workitem.getAsignee().getName().equalsIgnoreCase(filterType))
                .forEach(element -> stringBuilder.append(element.toString() + "\n"));

        ValidationCommands.isStringBuilderEmpty(stringBuilder);
        return stringBuilder.toString().trim();
    }
}
