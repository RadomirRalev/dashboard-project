package commands.actions;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import enums.CommandType;
import java.util.EnumSet;
import java.util.List;

public class ShowCommands extends ConsoleInteraction implements Command  {
    private final FunctionalsRepository functionalsRepository;
    private static EnumSet<CommandType> commands;

    public ShowCommands(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        commands = EnumSet.allOf(CommandType .class);
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {
        ConsoleInteraction.validateInput(parameters.size());
        //commandsList = COMMANDS_LIST;
        return commands.toString().replace("[", "")
                .replace("]", "")
                .replace(", ", "");
    }

    public void execute() {
        System.out.println(commands);
    }
}
