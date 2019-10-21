package commands.actions.workitem;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Priority;
import workitems.contracts.BugAndStory;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.PRIORITY_SUCCESSFULLY_CHANGED_MSG;
import static commands.actions.CommandsConstants.WORKITEM_NOT_BUG_OR_STORY;

public class ChangePriority extends ChangeBase implements Command {

    public ChangePriority(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String changeableParameter, WorkItems workitem) {
        BugAndStory bugAndStory;
        try {
            bugAndStory = (BugAndStory) workitem;
        } catch (ClassCastException ex){
            throw new IllegalArgumentException(WORKITEM_NOT_BUG_OR_STORY);
        }

        bugAndStory.setPriority(getPriority(changeableParameter));

        return String.format(PRIORITY_SUCCESSFULLY_CHANGED_MSG, workitemName, changeableParameter);
    }

    private Priority getPriority(String newPriority) {
        return Priority.valueOf(newPriority.toUpperCase());
    }
}