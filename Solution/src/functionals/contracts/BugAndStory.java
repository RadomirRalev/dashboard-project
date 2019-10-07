package functionals.contracts;

import enums.Priority;

public interface BugAndStory extends WorkItems {
    Priority getPriority();
    Person getAssignee();
    void setAssignee(Person assignee);
    void setPriority(Priority priority);
}
