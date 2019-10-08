package core;

import core.contracts.FunctionalsRepository;
import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FunctionalsRepositoryImpl implements FunctionalsRepository {

    private Map<String, Person> persons;
    private Map<String, Team> teams;

    public FunctionalsRepositoryImpl() {
        this.persons = new TreeMap<>();
        this.teams = new TreeMap<>();
    }

    @Override
    public Map<String, Person> getPersons() {
        return new TreeMap<>(persons);
    }

    @Override
    public Map<String, Team> getTeams(){
        return new TreeMap<>(teams);
    }

    public void addPerson(String name, Person person) {
        this.persons.put(name, person);
    }

    public void addTeam(String name, Team team){
        this.teams.put(name,team);
    }

}
