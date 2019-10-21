package core.factories;


import commands.actions.activityhistory.ShowActivityHistory;
//import commands.person.board.AddBoardToTeam;
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
import commands.actions.workitem.Change.*;
import commands.actions.workitem.Comments.AddComment;
import commands.actions.workitem.Comments.ShowComments;
import commands.actions.workitem.Create.CreateBug;
import commands.actions.workitem.Create.CreateFeedback;
import commands.actions.workitem.Create.CreateStory;
import commands.actions.workitem.ListWorkItems.*;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.CommandFactory;
import core.contracts.FunctionalsFactory;
import enums.CommandType;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command teamName: %s!";

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
            case LISTALLWORKITEMS:
                return new ListAllWorkItems(functionalsRepository);
            case LISTALLWORKITEMSBYTYPE:
                return new ListAllWorkItemsByType(functionalsRepository);
            case LISTALLWORKITEMSBYSTATUS:
                return new ListAllWorkItemsByStatus(functionalsRepository);
            case LISTALLWORKITEMSBYASSIGNEE:
                return new ListAllWorkItemsByAsignee(functionalsRepository);
            case SORTALLWORKITEMSBYTITLE:
                return new SortAllWorkItemsByTitle(functionalsRepository);
            case SORTALLSTORIESBYSIZE:
                return new SortAllStoriesBySize(functionalsRepository);
            case SORTALLBUGSBYSEVERITY:
                return new SortAllBugsBySeverity(functionalsRepository);
            case SORTALLWORKITEMSBYPRIORITY:
                return new SortAllWorkItemsByPriority(functionalsRepository);
            case SORTALLFEEDBACKBYRATING:
                return new SortAllFeedbackByRating(functionalsRepository);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
    }
}

