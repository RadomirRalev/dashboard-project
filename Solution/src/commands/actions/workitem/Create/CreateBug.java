package commands.actions.workitem.Create;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Bug;
import workitems.models.BugImpl;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static commands.actions.CommandsConstants.*;

public class CreateBug extends CreateWorkItem implements Command {
    private ArrayList<String> steps;

    public CreateBug(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        super(functionalsFactory, functionalsRepository);
    }

    @Override
    protected String createCommand(String title
            , String description
            , String boardName
            , String severity
            , FunctionalsFactory functionalsFactory
            , FunctionalsRepository functionalsRepository) {
        //adding the Bug to a specific board
        Bug bug = functionalsFactory.createBug(title, description, severity, steps);
        functionalsRepository.getBoards().get(boardName).addWorkItems(bug);

        //adding the Bug to the WorkItem Map.
        functionalsRepository.addWorkItem(bug.getId(), bug);

        return String.format(BUG_CREATED_SUCCESS_MESSAGE, title);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        setChangeableParameter(asksWhatWillItBe(SEVERITY));
        setChangeableParameter(ValidationCommands.checkIfEnumValueIsValid(getChangeableParameter()
                , BugImpl.getSeverityList()
                , SEVERITY
                , SEVERITIES));

        steps = Stream.of(asksWhat("steps to reproduce")
                .trim()
                .split("[.]"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
