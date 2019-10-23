package workitems.contracts;

import enums.Priority;
import functionals.contracts.Person;

import java.util.EnumSet;

public interface BugAndStory extends WorkItems {
    Priority getPriority();

    Person getAsignee();

    void setAsignee(Person asignee);

    void setPriority(Priority priority);
}
