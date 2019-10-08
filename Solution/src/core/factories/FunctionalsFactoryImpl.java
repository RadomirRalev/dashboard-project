package core.factories;

import core.contracts.FunctionalsFactory;
import functionals.contracts.Person;
import functionals.models.PersonImpl;

public class FunctionalsFactoryImpl implements FunctionalsFactory {

    @Override
    public Person createPerson(String name) {
        return new PersonImpl(name);
    }
}

