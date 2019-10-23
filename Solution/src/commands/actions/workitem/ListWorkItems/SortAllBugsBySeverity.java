package commands.actions.workitem.ListWorkItems;

import commands.actions.ValidationCommands;
import commands.actions.workitem.ListWorkItems.ListWorkItems;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Bug;

import java.util.Comparator;

import static commands.actions.CommandsConstants.BUG;

public class SortAllBugsBySeverity extends ListWorkItems implements Command {
    public SortAllBugsBySeverity(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> workitem.getItemType().equalsIgnoreCase(BUG))
                .map(workitem -> (Bug) workitem)
                .sorted(Comparator.comparingInt(workitem -> workitem.getSeverity().getWeight()))
                .forEach(item -> stringBuilder.append(item.toString() + "\n"));

        ValidationCommands.isStringBuilderEmpty(stringBuilder);
        return stringBuilder.toString().trim();
    }
}
