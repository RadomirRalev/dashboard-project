package commands.actions.workitem.Change;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Feedback;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.RATING_SUCCESSFULLY_CHANGED_MSG;
import static commands.actions.CommandsConstants.WORKITEM_NOT_FEEDBACK;

public class ChangeRating extends ChangeBase implements Command {
    public ChangeRating(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String newRating, WorkItems workitem) {
        Feedback feedback;
        try {
            feedback = (Feedback) workitem;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(WORKITEM_NOT_FEEDBACK);
        }

        feedback.setRating(getRating(newRating));

        return String.format(RATING_SUCCESSFULLY_CHANGED_MSG, workitemName, newRating);
    }

    private int getRating(String newRating) {
        return Integer.parseInt(newRating);
    }
}
