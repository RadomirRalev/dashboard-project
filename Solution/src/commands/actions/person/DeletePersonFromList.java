package commands.actions.person;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class DeletePersonFromList implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public DeletePersonFromList(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        String personName = NameJoiner.joinerList(parameters);
        personName = ValidationCommands.checkIfPersonExists(personName,functionalsRepository);
        return deletePerson(personName);
    }

    private String deletePerson(String name) {
        functionalsRepository.deletePerson(name);
        return String.format(PERSON_DELETED_MSG, name);
    }
}


