package commands.actions.person;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;

public class ShowPerson implements Command {

    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public ShowPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        String personName = ValidationCommands.asksWhichPerson();
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        return showPerson(personName);
    }

    private String showPerson(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return person.toString();
    }
}
