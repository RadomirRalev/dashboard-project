package commands.actions.person;

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
    public String execute(List<String> parameters) {
        String personName = NameJoiner.joiner(parameters);
        return deletePerson(personName);
    }

    private String deletePerson(String name) {
        if (!functionalsRepository.getPersons().containsKey(name)) {
            return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, name);
        }
        functionalsRepository.deletePerson(name);
        return String.format(PERSON_DELETED_MSG, name);
    }
}


