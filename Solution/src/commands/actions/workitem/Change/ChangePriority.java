package commands.actions.workitem.Change;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Priority;
import org.junit.Test;
import workitems.contracts.BugAndStory;
import workitems.contracts.WorkItems;
import workitems.models.BugAndStoryImpl;

import static commands.actions.CommandsConstants.*;

public class ChangePriority extends ChangeBase implements Command {

    public ChangePriority(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String newPriority, WorkItems workitem) {
        BugAndStory bugAndStory;
        try {
            bugAndStory = (BugAndStory) workitem;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(WORKITEM_NOT_BUG_OR_STORY);
        }

        bugAndStory.setPriority(getPriority(newPriority));
        bugAndStory.addHistory(String.format(PRIORITY_CHANGED_TO,newPriority));

        return String.format(PRIORITY_SUCCESSFULLY_CHANGED_MSG, workitemName, newPriority);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter((ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , BugAndStoryImpl.getPriorityList()
                , PRIORITY
                , PRIORITIES)));
    }

    protected String getChangeableParamterType() {
        return PRIORITY;
    }

    private Priority getPriority(String newPriority) {
        return Priority.valueOf(newPriority.toUpperCase());
    }
}