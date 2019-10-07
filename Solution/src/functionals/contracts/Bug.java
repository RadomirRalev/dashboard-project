package functionals.contracts;

import enums.Severity;

import java.util.List;

public interface Bug extends BugAndStory {
    List<String> getStepsToReproduce();
    Severity getSeverity();
    void setSeverity(Severity severity);
}
