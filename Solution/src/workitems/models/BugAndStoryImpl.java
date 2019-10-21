package workitems.models;

import enums.Priority;
import workitems.contracts.BugAndStory;
import functionals.contracts.Person;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

import java.util.EnumSet;

import static workitems.Constants.INVALID_ENUM_ERROR_MSG;

public abstract class BugAndStoryImpl extends WorkItemsImpl implements BugAndStory, WorkItems {
    private static EnumSet<Priority> possiblePriorities;

    private Priority priority;
    private Person asignee;

    static {
        possiblePriorities = EnumSet.allOf(Priority.class);
    }

    protected BugAndStoryImpl(String title, String description) {
        super(title, description);
        setPriority(Priority.NOTASSIGNED);
    }

    @Override
    public EnumSet<Priority> getPossiblePriorities() {
        return possiblePriorities;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Person getAsignee() {
        return asignee;
    }

    //setAsignee can be public so that you can set it after the WorkItems has been created;
    @Override
    public void setAsignee(Person asignee) {
        ValidationHelper.checkIfNull(asignee);
        this.asignee = asignee;
    }

    //setPriority can be public so that you can set it after the WorkItems has been created;
    @Override
    public void setPriority(Priority priority) {
        if (!possiblePriorities.contains(priority)) {
            throw new IllegalArgumentException(String.format(INVALID_ENUM_ERROR_MSG, priority, getItemType()));
        }
        this.priority = priority;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        if (getPriority() != null) {
            str.append(String.format("Priority: %s\n", getPriority()));
        }
        if (getAsignee() != null) {
            str.append(String.format("Person assigned: %s\n", asignee.getName()));
        }
        return str.toString();
    }
}
