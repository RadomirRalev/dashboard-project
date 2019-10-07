package functionals.contracts;

import enums.*;

import java.util.List;

public interface WorkItem {
    int getId();
    String getTitle();
    String getDescription();
    Status getStatus();
    List<String> getComments();
    List<String> getHistory();
    void setStatus(Status status);
    String toString();
}
