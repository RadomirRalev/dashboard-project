package Enums;

public enum Priority {
    HIGH ("High"),
    MEDIUM ("Medium"),
    LOW ("Low");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return priority;
    }
}
