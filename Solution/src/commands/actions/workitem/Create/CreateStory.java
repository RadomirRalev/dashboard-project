package commands.actions.workitem.Create;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Story;
import workitems.models.StoryImpl;

import static commands.actions.CommandsConstants.*;

public class CreateStory extends CreateWorkItem implements Command {
    public CreateStory(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        super(functionalsFactory, functionalsRepository);
    }

    protected String createCommand(String title
            , String description
            , String boardName
            , String size
            , FunctionalsFactory functionalsFactory
            , FunctionalsRepository functionalsRepository) {
        //adding the Story to a specific board
        Story story = functionalsFactory.createStory(title, description, size);
        functionalsRepository.getBoards().get(boardName).addWorkItems(story);

        //adding the Story to the WorkItem Map.
        functionalsRepository.addWorkItem(story.getId(), story);

        return String.format(STORY_CREATED_SUCCESS_MESSAGE, title);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter(asksWhatWillItBe(SIZE));
        setChangeableParameter(ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , StoryImpl.getSizeList()
                , SIZE
                , SIZES));
    }
}
