package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Person;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static commands.actions.CommandsConstants.*;

public class ListPersons implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ListPersons(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        StringJoiner str = new StringJoiner(", ");
        Set<String> keys = functionalsRepository.getPersons().keySet();
        for (String k : keys) {
            str.add(k);
        }
        String personsList = str.toString(); //in alphabetical order
        return listPersons(personsList);
    }

    private String listPersons(String personsList) {

        if (functionalsRepository.getPersons().size() == 0) {
            return PERSONSLIST_IS_EMPTY;
        }
        return String.format(PERSONSLIST_INCLUDES, personsList);
    }
}
