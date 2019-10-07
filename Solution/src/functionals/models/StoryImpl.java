package functionals.models;

import enums.Size;
import functionals.contracts.BugAndStory;
import functionals.contracts.Story;
import functionals.contracts.WorkItems;

public class StoryImpl extends BugAndStoryImpl implements Story, BugAndStory, WorkItems {
    private static final String ITEM_TYPE = "Story";

    private Size size;

    public StoryImpl(String title, String description, Size size) {
        super(title, description);
        setSize(size);
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("%sSize: %s", super.toString(), getSize());
    }

    @Override
    protected String getItemType() {
        return ITEM_TYPE;
    }

    private void setSize(Size size) {
        this.size = size;
    }

}
