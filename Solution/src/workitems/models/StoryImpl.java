package workitems.models;

import enums.Size;
import enums.Status;
import workitems.contracts.BugAndStory;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;

import java.util.EnumSet;

public class StoryImpl extends BugAndStoryImpl implements Story, BugAndStory, WorkItems {
    //TODO validation for Size
    private static final String ITEM_TYPE = "Story";

    private static final EnumSet<Status> storyStatus;
    private static final EnumSet<Size> storySize;

    private Size size;

    static {
        storyStatus = EnumSet.of(Status.NOTDONE, Status.IN_PROGRESS, Status.DONE);
        storySize = EnumSet.allOf(Size.class);
    }

    public StoryImpl(String title, String description, Size size) {
        super(title, description);
        setSize(size);
        setStatus(Status.NOTDONE);
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
    public String getItemType() {
        return ITEM_TYPE;
    }

    @Override
    public EnumSet<Status> getStatusList() {
        return storyStatus;
    }

    public static EnumSet<Size> getSizeList() {
        return storySize;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }
}
