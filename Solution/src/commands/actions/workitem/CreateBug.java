package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import workitems.contracts.Bug;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.BUG_CREATED_SUCCESS_MESSAGE;
import static commands.actions.CommandsConstants.FAILED_TO_PARSE_COMMAND_PARAMETERS;

public class CreateBug implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final FunctionalsRepository functionalsRepository;
    private final FunctionalsFactory functionalsFactory;

    private String title;
    private String description;
    private String severity;
    private List<String> steps;

    public CreateBug(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return createBug(title,description,severity,steps);
    }

    private String createBug(String title, String description, String severity, List<String> steps){
        Bug bug = functionalsFactory.createBug(title, description, severity, steps);
        functionalsRepository.addWorkItem(bug.getId(), bug);
        functionalsRepository.getBoards().get(name).addBug(bug);

        return String.format(BUG_CREATED_SUCCESS_MESSAGE, title);
    }

    private void parseParameters(List<String> parameters) {
        try {
            title = parameters.get(0);
            description = parameters.get(1);
            severity = parameters.get(2);
            steps = Arrays.stream(parameters.get(3).trim().split("[.]")).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
