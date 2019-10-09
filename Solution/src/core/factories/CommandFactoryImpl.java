package core.factories;

import commands.actions.CreateNewPerson;
import commands.actions.CreateTeam;
import commands.actions.ListPersons;
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
                case CREATETEAM:
                    return new CreateTeam(functionalsFactory, functionalsRepository);
            }
            throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }

