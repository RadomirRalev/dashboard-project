package enums;

public enum CommandType {
    CREATEPERSON("Createperson - Creates person\n"),
    LISTPERSONS("Listpersons - Lists all persons\n"),
    DELETEPERSON("Deleteperson - Deletes a person\n"),
    ASSIGNWORK("Assignwork - Adds work item to person\n"),
    LISTASSIGNEDWORK("Listassignedwork - Lists assigned work of a person\n"),
    CREATETEAM("Createteam - Creates team\n"),
    UNASSIGNWORK("Unassignwork - Deletes work item from a person\n"),
    ADDMEMBER("Addmember - Adds member to team\n"),
    LISTTEAMS("Listteams - Lists all teams\n"),
    CREATEBOARD("Createboard - Creates a board in a team\n"),
    SHOWTEAMBOARDS("Showteamboards - Shows boards of a team\n"),
    LISTALLMEMBERS("Listallmembers - Lists all team members\n"),
    CREATEBUG("Createbug - Creates a bug in a board\n"),
    CREATESTORY("Createstory - Creates story in a board\n"),
    SHOWACTIVITYHISTORY("Showactivityhistory - shows activity history\n"),
    CREATEFEEDBACK("Createfeedback - Creates a feedback in a board\n"),
    REMOVEWORKITEM("Removeworkitem - Removes work item\n"),
    SHOWPERSON("Showperson - Shows a specific person\n"),
    SHOWMEMBER("Showmember - Shows a specific team member\n"),
    CHANGESTATUS("Changestatus - Changes status\n"),
    REMOVEMEMBER("Removemember - Removes a member from team\n"),
    CHANGEPRIORITY("Changepriority - Changes priority\n"),
    CHANGERATING("Changerating - Changes rating\n"),
    CHANGESEVERITY("Changeseverity - Changes severity\n"),
    CHANGESIZE("Changesize - Changes size\n"),
    ADDCOMMENT("Addcomment - Adds a comment to a workitem\n"),
    SHOWCOMMENTS("Showcomment - Shows comments\n"),
    LISTALLWORKITEMS("ListAllWorkitems - Lists all work items\n"),
    LISTALLWORKITEMSBYTYPE("ListAllWorkitemsByType - Lists all work items filtered by type\n"),
    LISTALLWORKITEMSBYSTATUS("ListAllWorkitemsByStatus - Lists all work items filtered by status\n"),
    LISTALLWORKITEMSBYASIGNEE("ListAllWorkitemsByAsignee - Lists all work items filtered by assignee\n"),
    LISTALLWORKITEMSBYASIGNEEANDSTATUS("ListAllWorkitemsByAsigneeAndStatus - Lists all work items filtered by" +
            " assignee and status at the same time\n"),
    SORTALLWORKITEMSBYTITLE("SortAllWorkitemsByTitle - Sorts all work items by title\n"),
    SORTALLSTORIESBYSIZE("SortAllStoriesBySize - Sorts all stories by size\n"),
    SORTALLBUGSBYSEVERITY("SortAllStoriesBySeverity - Sorts all stories by severity\n"),
    SORTALLWORKITEMSBYPRIORITY("SortAllStoriesByPriority - Sorts all stories by priority\n"),
    SORTALLFEEDBACKBYRATING("SortAllFeedbackByRating - Sorts all feedback by rating\n"),
    SHOWCOMMANDS("ShowCommands - shows commands\n");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
