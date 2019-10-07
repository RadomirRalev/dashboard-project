package functionals.contracts;

import enums.*;

import java.util.List;

public interface WorkItems {
    int getId();
    String getTitle();
    String getDescription();
    Status getStatus();
    List<String> getComments();
    List<String> getHistory();
    void setStatus(Status status);
    void addHistory(String history);
    void addComment(String comment);
    @Override
    String toString();
}
