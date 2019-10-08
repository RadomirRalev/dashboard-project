package workitems.contracts;

import enums.Priority;
import functionals.contracts.Person;

public interface BugAndStory extends WorkItems {
    Priority getPriority();
    Person getAssignee();
    void setAssignee(Person assignee);
    void setPriority(Priority priority);
}
