package commands.actions.person;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.models.PersonImpl;

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
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        return prepareAssignedWorkList(personName);
    }

    private String prepareAssignedWorkList(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return person.listAssignedWork();
    }
}
