package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.PERSON_DOES_NOT_EXIST_ERROR_MSG;

public class ListAssignedWork implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ListAssignedWork(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        String personName = NameJoiner.joinerList(parameters);
        if (checkIfPersonExists(personName)) return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        return prepareAssignedWorkList(personName);
    }

    private String prepareAssignedWorkList(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return String.valueOf(person.listAssignedWork().stream()
                .map( n -> n.toString() )
                .collect( Collectors.joining( "; " ) ));
    }

    private boolean checkIfPersonExists(String personName) {
        return !functionalsRepository.getPersons().containsKey(personName);
    }
}
