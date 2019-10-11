package commands.actions.workitem;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import workitems.contracts.Bug;
import workitems.models.BugImpl;
import workitems.models.WorkItemsImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.*;

public class CreateBug implements Command {
    //creates a bug inside a board object. Cannot create bugs outside of boards (just like in Trello)
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 4;
    private final FunctionalsRepository functionalsRepository;
    private final FunctionalsFactory functionalsFactory;

    private String boardName;
    private String title;
    private String description;
    private String severity;
    private List<String> steps;
    private Reader reader;
    private Writer writer;

    public CreateBug(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        writer.writeLine("Please add the steps to reproduce this bug. Your steps will be split by the \".\" sign.");

        //Gets the steps to reproduce for a bug
        steps = Arrays.stream(reader.readLine().trim().split("[.]"))
                .collect(Collectors.toList());

        if(!functionalsRepository.getBoards().containsKey(boardName)){
            throw new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST_ERROR_MSG, boardName));
        }

        return createBug(title,description,severity,steps);
    }

    private String createBug(String title, String description, String severity, List<String> steps){
        //adding the Bug to a specific board
        Bug bug = functionalsFactory.createBug(title, description, severity, steps);
        functionalsRepository.getBoards().get(boardName).addWorkItems((BugImpl)bug);

        //adding the Bug to the WorkItem Map.
        functionalsRepository.addWorkItem(bug.getId(), bug);

        return String.format(BUG_CREATED_SUCCESS_MESSAGE, title);
    }

    private void parseParameters(List<String> parameters) {
        try {
            boardName = parameters.get(0);
            title = parameters.get(1);
            description = parameters.get(2);
            severity = parameters.get(3);
        } catch (Exception e) {
            throw new IllegalArgumentException(FAILED_TO_PARSE_COMMAND_PARAMETERS);
        }
    }
}
