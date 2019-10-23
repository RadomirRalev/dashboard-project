package commands.actions.workitem.Change;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Size;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;
import workitems.models.StoryImpl;

import static commands.actions.CommandsConstants.*;

public class ChangeSize extends ChangeBase implements Command {

    public ChangeSize(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String newSize, WorkItems workItem) {
        Story story;
        try {
            story = (Story) workItem;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(WORKITEM_NOT_STORY);
        }

        story.setSize(getSize(newSize));

        return String.format(SIZE_SUCCESSFULLY_CHANGED_MSG, workitemName, newSize);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter((ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , StoryImpl.getSizeList()
                , SIZE
                , SIZES)));
    }

    private Size getSize(String newSize) {
        return Size.valueOf(newSize.toUpperCase());
    }

    protected String getChangeableParamterType(){
        return SIZE;
    }
}
