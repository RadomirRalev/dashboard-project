package Enums;

public enum Severity {
    CRITICAL ("Critical"),
    MAJOR ("Major"),
    MINOR ("Minor");

    private final String severity;

    Severity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return severity;
    }
}
