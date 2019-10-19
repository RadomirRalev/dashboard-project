package commands.actions.person;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
import functionals.contracts.Person;
import functionals.models.PersonImpl;
import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateNewPerson extends ConsoleInteraction implements Command {
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepository functionalsRepository;

    public CreateNewPerson(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        personName = asksAboutPersonName();
        personName = ValidationCommands.checkNameOfNewPerson(personName, functionalsRepository);
        if (isCancel(personName)) return TYPE_ANOTHER_COMMAND;
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
