package functionals.contracts;

import workitems.contracts.WorkItems;
import java.util.List;

public interface Board extends Functionals {

    <T extends WorkItems> void addWorkItems(T workItem);

    <T extends WorkItems> void removeWorkItems(T workItem);

    List listWorkItems();

}
