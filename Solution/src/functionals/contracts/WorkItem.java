package functionals.contracts;

import enums.*;

import java.util.List;

public interface WorkItem {
    String getTitle();
    String getDescription();
    Status getStatus();
    List<String> getComments();
    List<String> getHistory();
    void changeStatus();
    String toString();
}
