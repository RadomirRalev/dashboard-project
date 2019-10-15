package commands.actions.person;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.models.PersonImpl;

import java.util.List;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.PERSON_DOES_NOT_EXIST_ERROR_MSG;
import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;

public class ListAssignedWork implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private String personName;

    public ListAssignedWork(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        return prepareAssignedWorkList(personName);
    }

    private String prepareAssignedWorkList(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return person.listAssignedWork();
    }

    private String getPersonName() {
        return personName;
    }

    private void setPersonName() {
        this.personName = ValidationCommands.asksWhichPerson();
    }
}
