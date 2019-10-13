package workitems.contracts;

import enums.Priority;
import functionals.contracts.Person;

import java.util.EnumSet;

public interface BugAndStory extends WorkItems {
    Priority getPriority();
    EnumSet<Priority> getPossiblePriorities();
    Person getAssignee();
    void setAssignee(Person assignee);
    void setPriority(Priority priority);
}
