package workitems.models;

import enums.Severity;
import enums.Status;
import workitems.contracts.Feedback;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

import java.util.EnumSet;

public class FeedbackImpl extends WorkItemsImpl implements Feedback, WorkItems {
    private static final String ITEM_TYPE = "Feedback";
    private static final int MIN_RATING_VALUE = 1;
    private static final int MAX_RATING_VALUE = 5;

    private static final EnumSet<Status> feedbackStatus;

    private int rating;

    static {
        feedbackStatus = EnumSet.of(Status.NEW, Status.UNSCHEDULED, Status.SCHEDULED, Status.DONE);
    }

    public FeedbackImpl(String title, String description, int rating) {
        super(title, description);
        setRating(rating);
        setStatus(Status.NEW);
        setId();
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append(String.format("Rating: %d\n", getRating()));
        return str.toString();
    }

    //rating can be a whole number between 1 and 5 inclusive
    public void setRating(int rating) {
        ValidationHelper.checkNumberInBounds(rating, MIN_RATING_VALUE, MAX_RATING_VALUE);
        this.rating = rating;
    }

    @Override
    public EnumSet<Status> getStatusList() {
        return feedbackStatus;
    }

    @Override
    public String getItemType() {
        return ITEM_TYPE;
    }


}
