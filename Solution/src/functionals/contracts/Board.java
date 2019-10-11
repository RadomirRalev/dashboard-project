package functionals.contracts;

import workitems.contracts.WorkItems;
import workitems.models.WorkItemsImpl;

import java.util.List;

public interface Board extends BoardAndPerson {
    <T extends WorkItemsImpl> void addWorkItems(T workItem);

    List listWorkItems();

    void addBug();

    void removeBug();

    void addStory();

    void removeStory();

    void addFeedback();

    void removeFeedback();

}
