package core;

import commands.actions.ConsoleInteraction;
import commands.contracts.Command;
import core.contracts.*;
import core.factories.CommandFactoryImpl;
import core.factories.FunctionalsFactoryImpl;
import core.providers.CommandParserImpl;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import java.util.List;

public class EngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String CONSOLE_INTRODUCTION = "Hello! You can start by creating a person or " +
            "a team through 'createperson' or 'createteam' and type 'exit' to leave this app.\n" +
            "You can check the available commands via 'showcommands'.";
    private Reader reader;
    private Writer writer;
    private CommandParser commandParser;
    private CommandFactory commandFactory;
    private FunctionalsFactory functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;

    static
    {
        System.out.println(CONSOLE_INTRODUCTION);
    }

    public EngineImpl() {
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
        commandParser = new CommandParserImpl();
        commandFactory = new CommandFactoryImpl();
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
    }

    @Override
    public void start() {
        while (true) {
            try {
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);

            } /*catch (IllegalArgumentException i) {
                writer.writeLine("Command does not exist. Please enter a new command or type 'exit' to close " +
                        "the application.");
            } */catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString());
            }
        }
    }

    private void processCommand(String commandAsString) throws IllegalArgumentException {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException("Command cannot be null or empty.");
        }

        String commandName = commandParser.parseCommand(commandAsString);
        Command command = commandFactory.createCommand(commandName, functionalsFactory, functionalsRepository);
        List<String> parameters = commandParser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        writer.writeLine(executionResult);
    }


}
