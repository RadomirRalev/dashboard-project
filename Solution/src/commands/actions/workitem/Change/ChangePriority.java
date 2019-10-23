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
    protected String changeCommand(String workitemName, String changeableParameter, WorkItems workitem) {
        BugAndStory bugAndStory;
        try {
            bugAndStory = (BugAndStory) workitem;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(WORKITEM_NOT_BUG_OR_STORY);
        }

        bugAndStory.setPriority(getPriority(changeableParameter));

        return String.format(PRIORITY_SUCCESSFULLY_CHANGED_MSG, workitemName, changeableParameter);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter((ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , BugAndStoryImpl.getPriorityList()
                , PRIORITY
                , PRIORITIES)));
    }

    private Priority getPriority(String newPriority) {
        return Priority.valueOf(newPriority.toUpperCase());
    }

    protected String getChangeableParamterType() {
        return PRIORITY;
    }
}