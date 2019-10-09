package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.Reader;
import core.providers.ConsoleReader;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateNewPerson implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;


    public CreateNewPerson(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String personName = parameters.get(0);
        while (checkName(personName)) {
            System.out.printf(PERSON_EXISTS_ERROR_MSG, personName);
            personName = reader.readLine();
        }
        return createPerson(personName);
    }

    private String createPerson(String name) {

        Person person = functionalsFactory.createPerson(name);
        functionalsRepository.addPerson(name, person);
        return String.format(PERSON_CREATED_MSG, name);
    }

    private boolean checkName(String personName) {
        return functionalsRepository.getPersons().containsKey(personName);
    }

}