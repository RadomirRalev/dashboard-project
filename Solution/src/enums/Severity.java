package enums;

public enum Severity {
    CRITICAL(3, "Critical"),
    MAJOR(2, "Major"),
    MINOR(1, "Minor");

    private final String severity;
    private final int weight;

    Severity(int weight, String severity) {
        this.weight = weight;
        this.severity = severity;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return severity;
    }
}
