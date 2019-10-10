package core.factories;

import commands.actions.board.AddBoardToTeam;
import commands.actions.board.CreateBoard;
import commands.actions.member.ListAllTeamMembers;
import commands.actions.team.ListTeams;
import commands.actions.member.CreateMember;
import commands.actions.person.*;
import commands.actions.team.CreateTeam;
import commands.actions.team.ShowTeamBoards;
import commands.actions.workitem.CreateBug;
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
                return new ListPersons(functionalsRepository);
            case DELETEPERSON:
                return new DeletePersonFromList(functionalsRepository);
            case ASSIGNWORK:
                return new AssignWorkToPerson(functionalsRepository);
            case LISTASSIGNEDWORK:
                return new ListAssignedWork(functionalsRepository);
            case CREATETEAM:
                return new CreateTeam(functionalsFactory, functionalsRepository);
            case UNASSIGNWORK:
                return new UnassignWorkFromPerson(functionalsRepository);
            case ADDMEMBER:
                return new CreateMember(functionalsRepository);
            case LISTTEAMS:
                return new ListTeams(functionalsRepository);
            case CREATEBOARD:
                return new CreateBoard(functionalsFactory, functionalsRepository);
            case ADDBOARDTOTEAM:
                return new AddBoardToTeam(functionalsFactory, functionalsRepository);
            case SHOWTEAMBOARDS:
                return new ShowTeamBoards(functionalsRepository);
            case LISTALLMEMBERS:
                return new ListAllTeamMembers(functionalsRepository);
            case CREATEBUG:
                return new CreateBug(functionalsFactory, functionalsRepository);

        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}

