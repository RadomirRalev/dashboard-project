package core.factories;

import core.contracts.FunctionalsFactory;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;

public class FunctionalsFactoryImpl implements FunctionalsFactory {

    @Override
    public Person createPerson(String name) {
        return new PersonImpl(name);
    }

    @Override
    public Team createTeam(String name){
        return new TeamsImpl(name);
    }

}

