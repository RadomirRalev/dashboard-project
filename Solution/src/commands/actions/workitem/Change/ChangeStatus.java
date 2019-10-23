package commands.actions.workitem.Change;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.Status;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.*;

public class ChangeStatus extends ChangeBase implements Command {

    public ChangeStatus(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String changeCommand(String boardName, String newStatus, WorkItems workItem) {
        workItem.setStatus(getStatus(newStatus));

        return String.format(STATUS_SUCCESSFULLY_CHANGED_MSG, workItem.getTitle(), newStatus);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter((ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , getWorkItem().getStatusList()
                , STATUS
                , getWorkItem().getStatusFilters())));
    }

    protected String getChangeableParamterType() {
        return STATUS;
    }

    private Status getStatus(String newStatus) {
        return Status.valueOf(newStatus.toUpperCase());
    }
}

