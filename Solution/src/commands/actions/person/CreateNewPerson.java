package commands.actions.person;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import functionals.contracts.Person;
import functionals.models.PersonImpl;
import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateNewPerson implements Command {
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private String personName;

    public CreateNewPerson(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        String personName = NameJoiner.joinerList(parameters);
        personName = ValidationCommands.checkNameOfNewPerson(personName, functionalsRepository);
        PersonImpl.getMembersActivity().put(personName, new ArrayList<>());
        return createPerson(personName);
    }

    private String createPerson(String name) {
        Person person = functionalsFactory.createPerson(name);
        functionalsRepository.addPerson(name, person);
        String activity = String.format(PERSON_CREATED_MSG, name);
        PersonImpl.addActivity(activity, name);
        return activity;
    }
}