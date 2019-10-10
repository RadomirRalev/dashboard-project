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
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        if(getPriority() != null){
            str.append(String.format("Priority: %s\n",getPriority()));
        }
        if(getAssignee() != null){
            str.append(String.format("Person assigned: %s\n",assignee.getName()));
        }
        return str.toString();
    }

    @Override
    protected abstract String getItemType();

}
