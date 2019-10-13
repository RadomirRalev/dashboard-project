package commands.actions.person;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class DeletePersonFromList implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public DeletePersonFromList(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        String personName = NameJoiner.joinerList(parameters);
        personName = ValidationCommands.checkIfPersonExists(personName,functionalsRepository);
        if (typeAnotherCommand(personName)) return TYPE_ANOTHER_COMMAND;
        return deletePerson(personName);
    }

    private boolean typeAnotherCommand(String personName) {
        return personName.equalsIgnoreCase("cancel");
    }

    private String deletePerson(String name) {
        functionalsRepository.deletePerson(name);
        return String.format(PERSON_DELETED_MSG, name);
    }
}


