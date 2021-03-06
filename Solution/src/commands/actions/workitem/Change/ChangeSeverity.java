package commands.actions.workitem.Change;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Severity;
import workitems.contracts.Bug;
import workitems.contracts.WorkItems;
import workitems.models.BugImpl;

import static commands.actions.CommandsConstants.*;

public class ChangeSeverity extends ChangeBase implements Command {

    public ChangeSeverity(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String workitemName, String newSeverity, WorkItems workitem) {
        Bug bug = ValidationCommands.castBug(workitem);

        bug.setSeverity(getSeverity(newSeverity));
        bug.addHistory(String.format(SEVERITY_CHANGED_TO, newSeverity));

        return String.format(SEVERITY_SUCCESSFULLY_CHANGED_MSG, workitemName, newSeverity);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter((ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , BugImpl.getSeverityList()
                , SEVERITY
                , SEVERITIES)));
    }

    protected String getChangeableParamterType() {
        return SEVERITY;
    }

    private Severity getSeverity(String newSeverity) {
        return Severity.valueOf(newSeverity.toUpperCase());
    }
}
