package commands.actions.workitem.Change;

import commands.actions.workitem.Change.ChangeBase;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Severity;
import workitems.contracts.Bug;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.*;

public class ChangeSeverity extends ChangeBase implements Command {

    public ChangeSeverity(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String newSeverity, WorkItems workitem) {
        Bug bug;
        try {
            bug = (Bug) workitem;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(WORKITEM_NOT_BUG);
        }

        bug.setSeverity(getSeverity(newSeverity));

        return String.format(SEVERITY_SUCCESSFULLY_CHANGED_MSG, workitemName, newSeverity);
    }

    private Severity getSeverity(String newSeverity) {
        return Severity.valueOf(newSeverity.toUpperCase());
    }
}
