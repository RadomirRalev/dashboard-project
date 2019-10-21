package commands.actions.workitem.Create;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Feedback;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateFeedback extends CreateWorkItem implements Command {

    public CreateFeedback(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        super(functionalsFactory, functionalsRepository);
    }

    @Override
    protected String createCommand(String title
            , String description
            , String boardName
            , String rating
            , FunctionalsFactory functionalsFactory
            , FunctionalsRepository functionalsRepository) {
        Feedback feedback = functionalsFactory.createFeedback(title, description, rating);
        functionalsRepository.getBoards().get(boardName).addWorkItems(feedback);

        functionalsRepository.addWorkItem(feedback.getId(), feedback);

        return String.format(FEEDBACK_CREATED_SUCCESS_MESSAGE, title);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter(asksWhatWillItBe("rating"));
        setChangeableParameter(ValidationCommands.checkIfRatingIsValid(getChangeableParameter()));
    }
}
