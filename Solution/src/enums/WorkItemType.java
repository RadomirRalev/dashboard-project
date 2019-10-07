package enums;

public enum WorkItemType {
    BUG ("BugImpl"),
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
