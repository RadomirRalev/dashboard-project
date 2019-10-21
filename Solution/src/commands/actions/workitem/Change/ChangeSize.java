package commands.actions.workitem.Change;

import commands.actions.workitem.Change.ChangeBase;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Size;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;

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

    private Size getSize(String newSize) {
        return Size.valueOf(newSize.toUpperCase());
    }
}