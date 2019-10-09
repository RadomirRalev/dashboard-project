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
    CREATEBOARD ("Create board");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
