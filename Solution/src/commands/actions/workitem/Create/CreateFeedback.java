package commands.actions.workitem.Create;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Feedback;
import workitems.models.FeedbackImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateFeedback implements Command {
    //creates feedback inside a board object. Cannot create feedback outside of boards (just like in Trello)
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;

    private final FunctionalsRepository functionalsRepository;
    private final FunctionalsFactory functionalsFactory;

    private String boardName;
    private String title;
    private String description;
    private int rating;

    public CreateFeedback(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        if (!functionalsRepository.getBoards().containsKey(boardName)) {
            throw new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST_ERROR_MSG, boardName));
        }

        return createFeedback(title, description, rating);
    }

    private String createFeedback(String title, String description, int rating) {
        Feedback feedback = functionalsFactory.createFeedback(title, description, rating);
        functionalsRepository.getBoards().get(boardName).addWorkItems(feedback);

        functionalsRepository.addWorkItem(feedback.getId(), feedback);

        return String.format(FEEDBACK_CREATED_SUCCESS_MESSAGE, title);
    }

    private void parseParameters(List<String> parameters) {
        try {
            boardName = parameters.get(0);
            title = parameters.get(1);
            description = parameters.get(2);
            rating = Integer.parseInt(parameters.get(3));
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
