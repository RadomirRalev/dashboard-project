package commands.actions.person;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;

public class ListAssignedWork extends ConsoleInteraction implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ListAssignedWork(FunctionalsRepositoryImpl functionalsRepository) {
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
        return prepareAssignedWorkList();
    }

    private String prepareAssignedWorkList() {
        StringBuilder str = new StringBuilder();
        Person person = functionalsRepository.getPersons().get(personName);
        for (int i = 0; i < person.getAssignedWork().size(); i++) {
            str.append(String.format("%s\n", person.getAssignedWork().get(i).toString()));
        }
        return str.toString();
    }
}
