package workitems.models;

import enums.Size;
import enums.Status;
import workitems.contracts.BugAndStory;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;

import java.util.EnumSet;

public class StoryImpl extends BugAndStoryImpl implements Story, BugAndStory, WorkItems {
    private static final String ITEM_TYPE = "Story";

    private static EnumSet<Status> storyStatus;

    private Size size;

    static {
        storyStatus = EnumSet.of(Status.NOTDONE, Status.IN_PROGRESS, Status.DONE);
    }

    public StoryImpl(String title, String description, Size size) {
        super(title, description);
        setSize(size);
        setId();
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

    @Override
    public EnumSet<Status> getStatus() {
        return storyStatus;
    }

    private void setSize(Size size) {
        this.size = size;
    }

}
