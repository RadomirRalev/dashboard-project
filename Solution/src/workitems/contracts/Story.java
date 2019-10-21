package workitems.contracts;

import enums.Size;
import workitems.contracts.BugAndStory;

public interface Story extends BugAndStory {
    Size getSize();

    void setSize(Size size);
}
