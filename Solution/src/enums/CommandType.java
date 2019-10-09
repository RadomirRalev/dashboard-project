package enums;

public enum CommandType {
    CREATEPERSON ("Create person"),
    LISTPERSONS ("List persons"),
    CREATETEAM ("Create team"),
    LISTTEAMS ("List teams");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
