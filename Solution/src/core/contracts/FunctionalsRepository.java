package core.contracts;

import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.Map;

public interface FunctionalsRepository {
    Map<String, Person> getPersons();
    Map<String, Team> getTeams();
}
