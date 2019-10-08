package core.contracts;

import functionals.contracts.Person;
import functionals.contracts.Team;

public interface FunctionalsFactory {
    Person createPerson(String name);
    Team createTeam(String name);
}
