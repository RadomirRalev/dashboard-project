package core;

import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.*;

public class FunctionalsRepositoryImpl implements FunctionalsRepository {

    private Map<String, Person> persons;
    private Map<String, Team> teams;
    private Map<String, Board> boards;
    private List<String> membersList;

    public FunctionalsRepositoryImpl() {
        this.persons = new TreeMap<>();
        this.teams = new TreeMap<>();
        this.boards = new TreeMap<>();
        this.membersList = new ArrayList();
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

    public List<String> getMembersList() {
        return membersList;
    }

    public void addPerson(String name, Person person) {
        this.persons.put(name, person);
    }

    public void addTeam(String name, Team team) {
        this.teams.put(name, team);
    }

    public void addBoard(String name, Board board) {
        this.boards.put(name, board);
    }

    public void deletePerson(String name) {
        this.persons.remove(name);
    }


}