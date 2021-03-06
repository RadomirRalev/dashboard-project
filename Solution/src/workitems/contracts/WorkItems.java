package workitems.contracts;

import enums.*;

import java.util.EnumSet;
import java.util.List;

public interface WorkItems {
    int getId();

    String getTitle();

    String getDescription();

    public abstract EnumSet<Status> getStatusList();

    Status getStatus();

    String getStatusFilters();

    List<String> getComments();

    List<String> getHistory();

    void setStatus(Status status);

    void addHistory(String history);

    void addComment(String comment);

    @Override
    String toString();

    String showComments();

    String getItemType();
}
