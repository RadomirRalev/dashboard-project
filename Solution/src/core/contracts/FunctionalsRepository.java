package core.contracts;

import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.MemberImpl;
import workitems.contracts.WorkItems;

import java.util.Map;
import java.util.TreeMap;

public interface FunctionalsRepository {
    Map<String, Person> getPersons();
    Map<String, Team> getTeams();
    Map<String, Board> getBoards();
    Map<Integer, WorkItems> getWorkItems();
    Map<String, MemberImpl> getMembers();
    void addWorkItem(Integer id, WorkItems workItems);
    void addBoard(String name, Board board);
    void addTeam(String name, Team team);
    void addPerson(String name, Person person);
    void deletePerson(String name);
    void addMember(String name, MemberImpl member);
    void removeWorkItem(Integer id);
}
