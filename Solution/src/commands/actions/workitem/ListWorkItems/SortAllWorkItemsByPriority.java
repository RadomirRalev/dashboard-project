package commands.actions.workitem.ListWorkItems;

import commands.actions.ValidationCommands;
import commands.actions.workitem.ListWorkItems.ListWorkItems;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.BugAndStory;

import java.util.Comparator;

import static commands.actions.CommandsConstants.FEEDBACK;

public class SortAllWorkItemsByPriority extends ListWorkItems implements Command {
    public SortAllWorkItemsByPriority(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> !workitem.getItemType().equalsIgnoreCase(FEEDBACK))
                .map(workitem -> (BugAndStory) workitem)
                .sorted(Comparator.comparingInt(workitem -> workitem.getPriority().getWeight()))
                .forEach(item -> stringBuilder.append(item.toString() + "\n"));

        ValidationCommands.isStringBuilderEmpty(stringBuilder);
        return stringBuilder.toString().trim();
    }
}
