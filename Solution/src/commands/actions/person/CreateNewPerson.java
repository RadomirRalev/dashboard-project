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

public class CreateNewPerson extends Persons implements Command {
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;

    public CreateNewPerson(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setPersonName();
        ValidationCommands.checkNameOfNewPerson(getPersonName(), functionalsRepository);
        PersonImpl.getMembersActivity().put(getPersonName(), new ArrayList<>());
        return createPerson(getPersonName());
    }

    private String createPerson(String name) {
        Person person = functionalsFactory.createPerson(name);
        functionalsRepository.addPerson(name, person);
        String activity = String.format(PERSON_CREATED_MSG, name);
        PersonImpl.addActivity(activity, name);
        return activity;
    }

}