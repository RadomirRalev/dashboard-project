package commands.actions.workitem.Create;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Story;
import workitems.models.StoryImpl;
import workitems.models.WorkItemsImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateStory implements Command {
    //creates a story inside a board object. Cannot create stories outside of boards (just like in Trello)
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final FunctionalsRepository functionalsRepository;
    private final FunctionalsFactory functionalsFactory;

    private String boardName;
    private String title;
    private String description;
    private String size;

    public CreateStory(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        ValidationCommands.checkIfItemExists(functionalsRepository.getBoards(), boardName);

//        if (!functionalsRepository.getBoards().containsKey(boardName)) {
//            throw new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST_ERROR_MSG, boardName));
//        }

        return createStory(title, description, size);
    }

    private String createStory(String title, String description, String size) {
        //adding the Story to a specific board
        Story story = functionalsFactory.createStory(title, description, size);
        functionalsRepository.getBoards().get(boardName).addWorkItems(story);

        //adding the Story to the WorkItem Map.
        functionalsRepository.addWorkItem(story.getId(), story);

        return String.format(STORY_CREATED_SUCCESS_MESSAGE, title);
    }

    private void parseParameters(List<String> parameters) {
        try {
            boardName = parameters.get(0);
            title = parameters.get(1);
            description = parameters.get(2);
            size = parameters.get(3);
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
