package functionals.models;

import enums.Priority;
import functionals.contracts.BugAndStory;
import functionals.contracts.Person;
import functionals.contracts.WorkItem;

public abstract class BugAndStoryImpl extends WorkItemImpl implements BugAndStory, WorkItem {
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

    //setAssignee can be public so that you can set it after the WorkItem has been created;
    public void setAssignee(Person assignee){
        ValidationHelper.checkIfNull(assignee);
        this.assignee = assignee;
    }

    //setPriority can be public so that you can set it after the WorkItem has been created;
    public void setPriority(Priority priority){
        this.priority = priority;
    }

    @Override
    protected abstract String getItemName();

}
