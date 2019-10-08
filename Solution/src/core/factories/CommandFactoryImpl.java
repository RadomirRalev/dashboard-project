package core.factories;

import commands.actions.*;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.CommandFactory;
import core.contracts.FunctionalsFactory;
import enums.CommandType;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommand(String commandTypeAsString,
                                 FunctionalsFactory functionalsFactory,
                                 FunctionalsRepositoryImpl functionalsRepository) {
        CommandType commandType = CommandType.valueOf(commandTypeAsString.toUpperCase().replace(" ", ""));
        switch (commandType) {
            case CREATEPERSON:
                return new CreateNewPerson(functionalsFactory, functionalsRepository);
            case LISTPERSONS:
                return new ListPersons(functionalsFactory, functionalsRepository);
            case DELETEPERSON:
                return new DeletePersonFromList(functionalsFactory, functionalsRepository);
            case ASSIGNWORK:
                return new AssignWorkToPerson(functionalsFactory, functionalsRepository);
            case LISTASSIGNEDWORK:
                return new ListAssignedWork(functionalsFactory, functionalsRepository);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}
