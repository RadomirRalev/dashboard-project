package commands.actions.person;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.models.PersonImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class UnassignWorkFromPerson extends Persons {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public UnassignWorkFromPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        asksAboutWorkToBeUnassigned();
        Person person = getPerson();
        if (ValidationCommands.checkIfWorkExists(asksAboutWorkToBeUnassigned(), person)) {
            return String.format(WORK_NOT_EXIST_MSG, asksAboutWorkToBeUnassigned());
        }
        person.unassignWork(asksAboutWorkToBeUnassigned() - 1);
        return addUnassignWorkToActivityHistory(getPersonName(), asksAboutWorkToBeUnassigned(), person);
    }

    private Person getPerson() {
        return functionalsRepository.getPersons().get(getPersonName());
    }

    private String addUnassignWorkToActivityHistory(String personName, int workToBeUnassigned, Person person) {
        String activity = String.format(WORK_UNASSIGNED, workToBeUnassigned, personName);
        PersonImpl.addActivity(activity, personName);
        return activity;
    }

    private int asksAboutWorkToBeUnassigned() {
        writer.writeLine("What number of assigned work to be removed?");
        return Integer.parseInt(reader.readLine());
    }
}
