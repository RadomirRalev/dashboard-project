package commands.actions.person;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;

public class ShowPerson extends ConsoleInteraction implements Command {

    private final FunctionalsRepositoryImpl functionalsRepository;

    public ShowPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        personName = asksAboutPersonName();
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        if (isCancel(personName)) {
            return TYPE_ANOTHER_COMMAND;
        }
        return showPerson(personName);
    }

    private String showPerson(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return person.toString();
    }
}

