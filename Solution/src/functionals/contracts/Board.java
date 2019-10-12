package functionals.contracts;

import workitems.contracts.WorkItems;
import workitems.models.WorkItemsImpl;

import java.util.List;

public interface Board extends Functionals {
    <T extends WorkItems> void addWorkItems(T workItem);

    <T extends WorkItems> void removeWorkItems(T workItem);

    List listWorkItems();

    void addBug();

    void removeBug();

    void addStory();

    void removeStory();

    void addFeedback();

    void removeFeedback();

}
