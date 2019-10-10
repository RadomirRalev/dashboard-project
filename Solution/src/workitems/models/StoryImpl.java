package workitems.models;

import enums.Size;
import workitems.contracts.BugAndStory;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;

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
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append(String.format("Size: %s\n", getSize()));
        return str.toString();
    }

    @Override
    protected String getItemType() {
        return ITEM_TYPE;
    }

    private void setSize(Size size) {
        this.size = size;
    }

}
