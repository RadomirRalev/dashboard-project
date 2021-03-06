package workitems.models;

import enums.Severity;
import enums.Status;
import workitems.contracts.Bug;
import workitems.contracts.BugAndStory;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static workitems.Constants.BUG_STATUSES;

public class BugImpl extends BugAndStoryImpl implements Bug, BugAndStory, WorkItems {
    private static final String ITEM_TYPE = "Bug";

    private static final EnumSet<Status> bugStatus;
    private static final EnumSet<Severity> severities;

    private Severity severity;
    private List<String> stepsToReproduce;

    static {
        bugStatus = EnumSet.of(Status.ACTIVE, Status.FIXED);
        severities = EnumSet.allOf(Severity.class);
    }

    public BugImpl(String title, String description, Severity severity, List<String> stepsToReproduce) {
        super(title, description);
        setSeverity(severity);
        setStepsToReproduce(stepsToReproduce);
        setStatus(Status.ACTIVE);
        setId();
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    //setSeverity can be public so it can be changed after creation;
    @Override
    public void setSeverity(Severity severity) {
        ValidationHelper.checkIfEnumValueIsValid(severity, getSeverityList());
        this.severity = severity;
    }

    @Override
    public EnumSet<Status> getStatusList() {
        return bugStatus;
    }

    public static EnumSet<Severity> getSeverityList() {
        return severities;
    }

    @Override
    public String getStatusFilters() {
        return BUG_STATUSES;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append(String.format("Severity: %s\n" +
                        "Steps needed to reproduce the bug: %s\n",
                getSeverity(), getStepsToReproduce()));
        return str.toString();
    }

    @Override
    public String getItemType() {
        return ITEM_TYPE;
    }

    private void setStepsToReproduce(List<String> stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }
}
