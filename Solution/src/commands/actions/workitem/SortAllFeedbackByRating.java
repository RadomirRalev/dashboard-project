package commands.actions.workitem;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Feedback;

import java.util.Comparator;

import static commands.actions.CommandsConstants.FEEDBACK;

public class SortAllFeedbackByRating extends ListWorkItems implements Command {
    public SortAllFeedbackByRating(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String listCommand(StringBuilder stringBuilder) {
        getFunctionalsRepository()
                .getWorkItems()
                .values()
                .stream()
                .filter(workitem -> workitem.getItemType().equalsIgnoreCase(FEEDBACK))
                .map(workitem -> (Feedback) workitem)
                .sorted(Comparator.comparingInt(Feedback::getRating))
                .forEach(item -> stringBuilder.append(item.toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}
