package Enums;

public enum WorkItemType {
    BUG ("Bug"),
    STORY ("Story"),
    FEEDBACK ("Feedback");

    private final String workitemtype;

    WorkItemType(String workitemtype) {
        this.workitemtype = workitemtype;
    }

    @Override
    public String toString() {
        return workitemtype;
    }
}
