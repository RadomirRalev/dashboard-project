package workitems.models;

import enums.Priority;
import workitems.contracts.BugAndStory;
import functionals.contracts.Person;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

public abstract class BugAndStoryImpl extends WorkItemsImpl implements BugAndStory, WorkItems {
    private Priority priority;
    private Person assignee;

    protected BugAndStoryImpl(String title, String description) {
        super(title, description);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Person getAssignee() {
        return assignee;
    }

    //setAssignee can be public so that you can set it after the WorkItems has been created;
    @Override
    public void setAssignee(Person assignee) {
        ValidationHelper.checkIfNull(assignee);
        this.assignee = assignee;
    }

    //setPriority can be public so that you can set it after the WorkItems has been created;
    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("%sPriority: %s\n" +
                        "Person assigned: %s\n", super.toString(),
                getPriority(), assignee.getName());
    }

    @Override
    protected abstract String getItemType();

}
