package workitems.models;

import workitems.contracts.Feedback;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

public class FeedbackImpl extends WorkItemsImpl implements Feedback, WorkItems {
    private static final String ITEM_TYPE = "Feedback";
    private static final int MIN_RATING_VALUE = 1;
    private static final int MAX_RATING_VALUE = 5;

    private int rating;

    public FeedbackImpl(String title, String description, int rating) {
        super(title, description);
        setRating(rating);
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("%sRating: %d", super.toString(), getRating());
    }

    @Override
    protected String getItemType() {
        return ITEM_TYPE;
    }

    //rating can be a whole number between 1 and 5 inclusive
    private void setRating(int rating) {
        ValidationHelper.checkNumberInBounds(rating, MIN_RATING_VALUE, MAX_RATING_VALUE);
        this.rating = rating;
    }
}
