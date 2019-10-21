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
    CREATEBOARD("Createboard X Y - Creates board where X is the name of the board and Y is the name of the team " +
            "it is assigned to\n"),
    SHOWTEAMBOARDS("Showteamboards - Shows boards of a team\n"),
    LISTALLMEMBERS("Listallmembers - Lists all team members\n"),
    CREATEBUG("Createbug X Y Z A - Creates bug where X is its board name, Y is its title, Z is description and " +
            "A is severity\n"),
    CREATESTORY("Createstory X Y Z A - Creates story where X is its board name, Y is its title, Z is description and " +
            "A is size\n"),
    SHOWACTIVITYHISTORY("Showactivityhistory - shows activity history\n"),
    CREATEFEEDBACK("Createfeedback X Y Z A - Creates bug where X is its name, Y is its title, Z is description and " +
            "A is rating\n"),
    REMOVEWORKITEM("Removeworkitem - Removes work item\n"),
    SHOWPERSON("Showperson - Shows a specific person\n"),
    SHOWMEMBER("Showmember - Shows a specific team member\n"),
    CHANGESTATUS("Changestatus - Changes status\n"),
    REMOVEMEMBER("Removemember - Removes a member from team\n"),
    CHANGEPRIORITY("Changepriority - Changes priority\n"),
    CHANGERATING("Changerating - Changes rating\n"),
    CHANGESEVERITY("Changeseverity - Changes severity\n"),
    CHANGESIZE("Changesize - Changes size\n"),
    ADDCOMMENT("Addcomment X Y Z A - Adds comment where X is board name, Y is its title, Z is description and " +
            "A is rating\n"),
    SHOWCOMMENTS("Showcomment - Shows comments\n"),
    LISTALLWORKITEMS("Listallworkitems - Lists all work items\n"),
    LISTALLWORKITEMSBYTYPE("Listallworkitemsbytype - Lists all work items by type\n"),
    LISTALLWORKITEMSBYSTATUS("Listallworkitemsbystatus - Lists all work items by status\n"),
    LISTALLWORKITEMSBYASSIGNEE("Listallworkitemsbyasignee - Lists all work items by assignee\n"),
    SORTALLWORKITEMSBYTITLE("Sortallworkitemsbytitle - Sorts all work items by title\n"),
    SORTALLSTORIESBYSIZE("Sortallstoriesbysize - Sorts all stories by size\n"),
    SORTALLBUGSBYSEVERITY("Sortallstoriesbyseverity - Sorts all stories by severity\n"),
    SORTALLWORKITEMSBYPRIORITY("Sortallstoriesbypriority - Sorts all stories by priority\n"),
    SORTALLFEEDBACKBYRATING("Sortallfeedbackbyrating - Sorts all feedback by rating\n"),
    SHOWCOMMANDS("Showcommands - shows commands\n");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
