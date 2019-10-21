package commands.actions.workitem.ListWorkItems;

import commands.actions.workitem.ListWorkItems.ListWorkItems;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Story;

import java.util.Comparator;

import static commands.actions.CommandsConstants.STORY;

public class SortAllStoriesBySize extends ListWorkItems implements Command {
    public SortAllStoriesBySize(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(item -> item.getItemType().equalsIgnoreCase(STORY))
                .map(workitem -> (Story) workitem)
                .sorted(Comparator.comparingInt(workitem -> workitem.getSize().getWeight()))
                .forEach(item -> stringBuilder.append(item.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
