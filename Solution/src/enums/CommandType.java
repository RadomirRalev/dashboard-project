package enums;

public enum CommandType {
    CREATEPERSON ("Create person");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

}
