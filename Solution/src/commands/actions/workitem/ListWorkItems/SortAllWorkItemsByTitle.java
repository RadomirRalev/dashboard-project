package commands.actions.workitem.ListWorkItems;

import commands.actions.ValidationCommands;
import commands.actions.workitem.ListWorkItems.ListWorkItems;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.WorkItems;

import java.util.Comparator;

public class SortAllWorkItemsByTitle extends ListWorkItems implements Command {
    public SortAllWorkItemsByTitle(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .sorted(Comparator.comparing(WorkItems::getTitle))
                .forEach(workitem -> stringBuilder.append(workitem.toString() + "\n"));

        ValidationCommands.isStringBuilderEmpty(stringBuilder);
        return stringBuilder.toString().trim();
    }
}
