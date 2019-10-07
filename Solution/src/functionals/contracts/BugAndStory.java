package functionals.contracts;

import enums.Priority;

public interface BugAndStory extends WorkItem {
    Priority getPriority();
    Person getAssignee();
}
