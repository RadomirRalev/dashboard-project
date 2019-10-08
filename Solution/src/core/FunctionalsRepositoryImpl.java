package core;

import core.contracts.FunctionalsRepository;
import functionals.contracts.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FunctionalsRepositoryImpl implements FunctionalsRepository {

    private Map<String, Person> persons;

    public FunctionalsRepositoryImpl() {
        this.persons = new TreeMap<>();
    }

    @Override
    public Map<String, Person> getPersons() {
        return new TreeMap<>(persons);
    }

    public void addPerson(String name, Person person) {
        this.persons.put(name, person);
    }

}
