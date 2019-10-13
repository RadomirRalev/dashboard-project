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

public class AssignWorkToPerson implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public AssignWorkToPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        String personName = NameJoiner.joinerList(parameters);
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        if (typeAnotherCommand(personName)) return TYPE_ANOTHER_COMMAND;
        String workToBeAdded = asksAboutWorkToBeAdded();
        Person person = addsWorkToPerson(personName, workToBeAdded);
        return addsWorkToActivityHistory(personName, workToBeAdded, person);
    }

    private boolean typeAnotherCommand(String personName) {
        return personName.equalsIgnoreCase("cancel");
    }

    private String addsWorkToActivityHistory(String personName, String workToBeAdded, Person person) {
        String activity = String.format(WORK_ADDED_MSG, workToBeAdded, personName);
        PersonImpl.addActivity(activity, personName);
        return activity;
    }

    private Person addsWorkToPerson(String personName, String workToBeAdded) {
        Person person = functionalsRepository.getPersons().get(personName);
        person.assignWork(workToBeAdded);
        return person;
    }

    private String asksAboutWorkToBeAdded() {
        writer.writeLine("What work will be added?");
        return reader.readLine();
    }
}
