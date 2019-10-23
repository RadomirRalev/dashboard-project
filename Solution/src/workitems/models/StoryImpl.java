package workitems.models;

import enums.Size;
import enums.Status;
import functionals.models.ValidationHelper;
import workitems.contracts.BugAndStory;
import workitems.contracts.Story;
import workitems.contracts.WorkItems;

import java.util.EnumSet;

import static workitems.Constants.STORY_STATUSES;

public class StoryImpl extends BugAndStoryImpl implements Story, BugAndStory, WorkItems {
    private static final String ITEM_TYPE = "Story";

    private static final EnumSet<Status> storyStatus;
    private static final EnumSet<Size> storySize;

    private Size size;

    static {
        storyStatus = EnumSet.of(Status.NOTDONE, Status.INPROGRESS, Status.DONE);
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
    public String getStatusFilters(){
        return STORY_STATUSES;
    }

    @Override
    public void setSize(Size size) {
        ValidationHelper.checkIfEnumValueIsValid(size, getSizeList());
        this.size = size;
    }
}
