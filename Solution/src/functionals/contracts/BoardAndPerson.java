package functionals.contracts;

import workitems.models.WorkItemsImpl;

import java.util.List;

public interface BoardAndPerson extends Functionals {

    void addActivity(String activity);

    void removeActivity();

    List showActivity();

}
