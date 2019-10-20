package commands.actions.workitem;

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

    protected String changeCommand(String workitemName, String newSeverity, WorkItems workitem) {
        Bug bug = (Bug) workitem;

        bug.setSeverity(getSeverity(newSeverity));

        return String.format(SEVERITY_SUCCESSFULLY_CHANGED_MSG, workitemName, newSeverity);
    }

    private Severity getSeverity(String newSeverity) {
        return Severity.valueOf(newSeverity.toUpperCase());
    }
}
