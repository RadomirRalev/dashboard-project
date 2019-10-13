package commands.actions.person;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.models.PersonImpl;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class UnassignWorkFromPerson implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public UnassignWorkFromPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        String personName = NameJoiner.joinerList(parameters);
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        int workToBeUnassigned = asksAboutWorkToBeUnassigned();
        Person person = functionalsRepository.getPersons().get(personName);
        if (ValidationCommands.checkIfWorkExists(workToBeUnassigned, person)) return String.format(WORK_NOT_EXIST_MSG, workToBeUnassigned);
        person.unassignWork(workToBeUnassigned - 1);
        return addUnassignWorkToActivityHistory(personName, workToBeUnassigned, person);
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
