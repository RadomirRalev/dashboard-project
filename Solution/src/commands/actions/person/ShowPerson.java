package commands.actions.person;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;

public class ShowPerson extends PersonName implements Command {

    private final FunctionalsRepositoryImpl functionalsRepository;

    public ShowPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        return showPerson(getPersonName());
    }

    private String showPerson(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return person.toString();
    }
}
