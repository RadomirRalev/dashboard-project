package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class DeletePersonFromList implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public DeletePersonFromList(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String personName = parameters.get(0);
        return deletePerson(personName);
    }

    private String deletePerson(String name) {

        if (!functionalsRepository.getPersons().containsKey(name)) {
            return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, name);
        }

        functionalsRepository.deletePerson(name);
        return String.format(PERSON_DELETED_MSG, name);
    }
}


