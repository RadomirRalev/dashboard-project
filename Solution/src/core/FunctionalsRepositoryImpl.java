package core;

import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.MemberImpl;
import workitems.contracts.WorkItems;

import java.util.*;

public class FunctionalsRepositoryImpl implements FunctionalsRepository {

    private Map<String, Person> persons;
    private Map<String, Team> teams;
    private Map<String, Board> boards;
    private Map<String, Person> members;
    private Map<Integer, WorkItems> workItems;

    public FunctionalsRepositoryImpl() {
        this.persons = new TreeMap<>();
        this.teams = new TreeMap<>();
        this.boards = new TreeMap<>();
        this.members = new TreeMap<>();
        this.workItems = new TreeMap<>();
    }

    @Override
    public Map<String, Person> getPersons() {
        return new TreeMap<>(persons);
    }

    @Override
    public Map<String, Team> getTeams() {
        return new TreeMap<>(teams);
    }

    @Override
    public Map<String, Board> getBoards() {
        return new TreeMap<>(boards);
    }

    @Override
    public Map<Integer, WorkItems> getWorkItems() {
        return new TreeMap<>(workItems);
    }

    public Map<String, Person> getMembers() {
        return new TreeMap<>(members);
    }

    @Override
    public void addPerson(String name, Person person) {
        this.persons.put(name, person);
    }

    @Override
    public void addTeam(String name, Team team) {
        this.teams.put(name, team);
    }

    @Override
    public void addBoard(String name, Board board) {
        this.boards.put(name, board);
    }

    @Override
    public void deletePerson(String name) {
        this.persons.remove(name);
    }

    @Override
    public void addMember(String name, Person person) {
        this.members.put(name, person);
    }

    @Override
    public void addWorkItem(Integer id, WorkItems workItems) {
        this.workItems.put(id, workItems);
    }

    @Override
    public void removeWorkItem(Integer id){
        workItems.remove(id);
    }
}
