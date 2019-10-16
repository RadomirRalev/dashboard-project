package commands.actions.person;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;


import java.util.List;

import static commands.actions.CommandsConstants.*;

public class DeletePersonFromList extends PersonName implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private String personName;

    public DeletePersonFromList(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        return deletePerson(getPersonName());
    }

    private String deletePerson(String name) {
        functionalsRepository.deletePerson(name);
        return String.format(PERSON_DELETED_MSG, name);
    }
}


