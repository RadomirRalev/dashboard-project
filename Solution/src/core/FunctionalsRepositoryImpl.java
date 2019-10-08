package core;

import core.contracts.FunctionalsRepository;
import functionals.contracts.Person;

import java.util.HashMap;
import java.util.Map;

public class FunctionalsRepositoryImpl implements FunctionalsRepository {

    private Map<String, Person> persons;
    public FunctionalsRepositoryImpl() {
        this.persons = new HashMap<>();
    }

    @Override
    public Map<String, Person> getPersons() {
        return new HashMap<>(persons);
    }

    public void addPerson(String name, Person person) {
        this.persons.put(name, person);
    }

}
