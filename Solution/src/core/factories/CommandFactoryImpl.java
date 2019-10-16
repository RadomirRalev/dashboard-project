package core.factories;


import commands.actions.activityhistory.ShowActivityHistory;
//import commands.actions.board.AddBoardToTeam;
import commands.actions.board.CreateBoard;
import commands.actions.person.ListAllTeamMembers;
import commands.actions.person.RemoveMember;
import commands.actions.person.ShowMember;
import commands.actions.team.ListTeams;
import commands.actions.person.AddPersonToTeam;
import commands.actions.person.*;
import commands.actions.team.CreateTeam;
import commands.actions.team.ShowTeamBoards;
import commands.actions.workitem.*;
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
                return new AddPersonToTeam(functionalsRepository);
            case LISTTEAMS:
                return new ListTeams(functionalsRepository);
            case CREATEBOARD:
                return new CreateBoard(functionalsFactory, functionalsRepository);
            case SHOWTEAMBOARDS:
                return new ShowTeamBoards(functionalsRepository);
            case LISTALLMEMBERS:
                return new ListAllTeamMembers(functionalsRepository);
            case CREATEBUG:
                return new CreateBug(functionalsFactory, functionalsRepository);
            case CREATESTORY:
                return new CreateStory(functionalsFactory, functionalsRepository);
            case SHOWACTIVITYHISTORY:
                return new ShowActivityHistory(functionalsRepository);
            case CREATEFEEDBACK:
                return new CreateFeedback(functionalsFactory, functionalsRepository);
            case REMOVEWORKITEM:
                return new RemoveWorkItem(functionalsRepository);
            case SHOWPERSON:
                return new ShowPerson(functionalsRepository);
            case SHOWMEMBER:
                return new ShowMember(functionalsRepository);
            case CHANGESTATUS:
                return new ChangeStatus(functionalsRepository);
            case REMOVEMEMBER:
                return new RemoveMember(functionalsRepository);
            case CHANGEPRIORITY:
                return new ChangePriority(functionalsRepository);
            case CHANGERATING:
                return new ChangeRating(functionalsRepository);
            case CHANGESEVERITY:
                return new ChangeSeverity(functionalsRepository);
            case CHANGESIZE:
                return new ChangeSize(functionalsRepository);
            case ADDCOMMENT:
                return new AddComment(functionalsRepository);
            case SHOWCOMMENTS:
                return new ShowComments(functionalsRepository);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}

