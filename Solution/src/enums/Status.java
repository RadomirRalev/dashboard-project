package enums;

public enum Status {

    ACTIVE ("Active"),
    FIXED ("Fixed"),
    NEW ("New"),
    UNSCHEDULED ("Unscheduled"),
    SCHEDULED ("Scheduled"),
    DONE ("Done"),
    NOTDONE ("NotDone"),
    INPROGRESS("InProgress");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    /* I did a single enum for Bugs, Feedback and Story, in order to make the code more logical. We can access
    the specific subsets for Bugs, Feedback and Story through the EnumSet implementation of Set interface. For example,
    in the Bugs class:
                EnumSet<Status> bugStatus = EnumSet.of(Status.ACTIVE, Status.FIXED);
    Let's try if it works :)

     */

}
