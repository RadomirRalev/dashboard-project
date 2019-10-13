package enums;

public enum CommandType {
    CREATEPERSON ("Create person"),
    LISTPERSONS ("List persons"),
    DELETEPERSON ("Delete person"),
    ASSIGNWORK ("Assign work"),
    LISTASSIGNEDWORK ("List assigned work"),
    CREATETEAM ("Create team"),
    UNASSIGNWORK ("Unassign work"),
    ADDMEMBER ("Add member"),
    LISTTEAMS ("List teams"),
    CREATEBOARD ("Create board"),
    SHOWTEAMBOARDS ("Show team boards"),
    LISTALLMEMBERS ("List all members"),
    CREATEBUG ("Create bug"),
 	CREATESTORY ("Create story"),
    SHOWACTIVITYHISTORY ("Show activity history"),
    CREATEFEEDBACK ("Create feedbak"),
    REMOVEWORKITEM ("Remove workitem"),
    SHOWPERSON ("Show person"),
    SHOWMEMBER ("Show person"),
    CHANGESTATUS ("Change status"),
    CHANGEPRIORITY ("Change priority");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
