package workitems.models;

import enums.Severity;
import workitems.contracts.Bug;
import workitems.contracts.BugAndStory;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends BugAndStoryImpl implements Bug, BugAndStory, WorkItems {
    private static final String ITEM_TYPE = "Bug";

    private Severity severity;
    private List<String> stepsToReproduce;

    public BugImpl(String title, String description, Severity severity, List<String> stepsToReproduce) {
        super(title, description);
        setSeverity(severity);
        setStepsToReproduce(stepsToReproduce);
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
        this.severity = severity;
    }

    @Override
    public String toString() {
        return String.format("%sSeverity: %s\n" +
                        "Steps needed to reproduce the bug: %s\n", super.toString(),
                getSeverity(), getStepsToReproduce());
    }

    @Override
    protected String getItemType() {
        return ITEM_TYPE;
    }

    private void setStepsToReproduce(List<String> stepsToReproduce) {
        ValidationHelper.checkIfNull(stepsToReproduce);
        this.stepsToReproduce = stepsToReproduce;
    }
}
