package commands.actions;
import commands.contracts.Command;
import core.contracts.FunctionalsRepository;

import java.util.List;

import static commands.actions.CommandsConstants.COMMANDS_LIST;

public class ShowCommands extends ConsoleInteraction implements Command  {
    private final FunctionalsRepository functionalsRepository;
    private String commandsList;

    public ShowCommands(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
        ConsoleInteraction.validateInput(parameters.size());
        commandsList = COMMANDS_LIST;
        return commandsList;
    }

    public void execute(String commandsList) {
        System.out.println(commandsList);
    }
}
