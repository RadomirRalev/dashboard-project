package commands.actions.workitem;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Feedback;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.RATING_SUCCESSFULLY_CHANGED_MSG;

public class ChangeRating extends ChangeBase implements Command {
    //TODO make a validation for the casting of feedback

    public ChangeRating(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String newRating, WorkItems workitem) {
        Feedback feedback = (Feedback) workitem;

        feedback.setRating(getRating(newRating));

        return String.format(RATING_SUCCESSFULLY_CHANGED_MSG, workitemName, newRating);
    }

    private int getRating(String newRating) {
        return Integer.parseInt(newRating);
    }
}
