package core.factories;

import commands.actions.board.CreateBoard;
import commands.actions.team.ListTeams;
import commands.actions.member.CreateMember;
import commands.actions.person.*;
import commands.actions.team.CreateTeam;
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
            case CREATETEAM:
                return new CreateTeam(functionalsFactory, functionalsRepository);
            case UNASSIGNWORK:
                return new UnassignWorkFromPerson(functionalsFactory, functionalsRepository);
            case ADDMEMBER:
                return new CreateMember(functionalsFactory, functionalsRepository);
            case LISTTEAMS:
                return new ListTeams(functionalsRepository);
            case CREATEBOARD:
                return new CreateBoard(functionalsFactory, functionalsRepository);

        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}

