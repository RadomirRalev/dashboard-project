package enums;

public enum CommandType {
    CREATEPERSON("Create person"),
    LISTPERSONS("List persons"),
    DELETEPERSON("Delete person"),
    ASSIGNWORK("Assign work"),
    LISTASSIGNEDWORK("List assigned work"),
    CREATETEAM("Create team"),
    UNASSIGNWORK("Unassign work"),
    ADDMEMBER("Add member"),
    LISTTEAMS("List teams"),
    CREATEBOARD("Create board"),
    SHOWTEAMBOARDS("Show team boards"),
    LISTALLMEMBERS("List all members"),
    CREATEBUG("Create bug"),
    CREATESTORY("Create story"),
    SHOWACTIVITYHISTORY("Show activity history"),
    CREATEFEEDBACK("Create feedbak"),
    REMOVEWORKITEM("Remove workitem"),
    SHOWPERSON("Show person"),
    SHOWMEMBER("Show person"),
    CHANGESTATUS("Change status"),
    REMOVEMEMBER("Remove member"),
    CHANGEPRIORITY("Change priority"),
    CHANGERATING("Change rating"),
    CHANGESEVERITY("Change severity"),
    CHANGESIZE("Change size"),
    ADDCOMMENT("Add comment"),
    SHOWCOMMENTS("Show comments"),
    LISTALLWORKITEMS("List all workitems"),
    LISTALLWORKITEMSBYTYPE("List all workitems by type"),
    LISTALLWORKITEMSBYSTATUS("List all workitems by status"),
    LISTALLWORKITEMSBYASSIGNEE("List all workitems by assignee"),
    SORTALLWORKITEMSBYTITLE("Sort all workitems by title"),
    SORTALLSTORIESBYSIZE("Sort all stories by size"),
    SORTALLBUGSBYSEVERITY("Sort all bugs by severity"),
    SORTALLWORKITEMSBYPRIORITY("Sort all workitems by priority"),
    SORTALLFEEDBACKBYRATING("Sort all feedback by rating"),
    SHOWCOMMANDS("Show commands");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
