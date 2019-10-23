package commands.actions.workitem.Change;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Feedback;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.*;

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
        feedback.addHistory(String.format(RATING_CHANGED_TO, newRating));

        return String.format(RATING_SUCCESSFULLY_CHANGED_MSG, workitemName, newRating);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter(ValidationCommands.checkIfRatingIsValid(getChangeableParameter()));
    }

    protected String getChangeableParamterType() {
        return RATING;
    }

    private int getRating(String newRating) {
        return Integer.parseInt(newRating);
    }
}
