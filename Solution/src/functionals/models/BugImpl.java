package functionals.models;

import enums.Severity;
import functionals.contracts.Bug;
import functionals.contracts.BugAndStory;
import functionals.contracts.WorkItems;

import java.util.List;

public class BugImpl extends BugAndStoryImpl implements Bug, BugAndStory, WorkItems {
    private static final String bugName = "Bug";

    private Severity severity;
    private List<String> stepsToReproduce;

    public BugImpl(String title, String description, Severity severity, List<String> stepsToReproduce) {
        super(title, description);
        setSeverity(severity);
        setStepsToReproduce(stepsToReproduce);
    }

    @Override
    public List<String> getStepsToReproduce(){
        return stepsToReproduce;
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
    protected String getItemName() {
        return bugName;
    }

    private void setStepsToReproduce(List<String> stepsToReproduce){
        ValidationHelper.checkIfNull(stepsToReproduce);
        this.stepsToReproduce = stepsToReproduce;
    }
}
