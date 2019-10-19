package commands.actions.person;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;


import java.util.List;

import static commands.actions.CommandsConstants.*;

public class DeletePersonFromList extends ConsoleInteraction implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;

    public DeletePersonFromList(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        personName = asksAboutPersonName();
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        if (isCancel(personName)) return TYPE_ANOTHER_COMMAND;
        return deletePerson(personName);
    }

    private String deletePerson(String name) {
        functionalsRepository.deletePerson(name);
        return String.format(PERSON_DELETED_MSG, name);
    }
}



