package core.contracts;

import functionals.contracts.Person;

import java.util.Map;

public interface FunctionalsRepository {
    Map<String, Person> getPersons();

}
