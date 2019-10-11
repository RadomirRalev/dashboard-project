package core.contracts;

import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import workitems.contracts.WorkItems;

import java.util.Map;

public interface FunctionalsRepository {
    Map<String, Person> getPersons();
    Map<String, Team> getTeams();
    Map<String, Board> getBoards();
    Map<Integer, WorkItems> getWorkItems();
    void addWorkItem(Integer id, WorkItems workItems);
}
