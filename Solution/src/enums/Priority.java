package enums;

public enum Priority {
    HIGH(3, "High"),
    MEDIUM(2, "Medium"),
    LOW(1, "Low"),
    NOTASSIGNED(0, "NotAssigned");

    private final String priority;
    private final int weight;

    Priority(int weight, String priority) {
        this.weight = weight;
        this.priority = priority;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return priority;
    }
}
