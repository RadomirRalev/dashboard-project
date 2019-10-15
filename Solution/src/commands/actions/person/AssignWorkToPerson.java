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
    private String personName;
    private String workToBeAdded;


    public AssignWorkToPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        asksAboutWorkToBeAdded();
        Person person = getPerson();
        return addsWorkToActivityHistory(personName, getWorkToBeAdded(), person);
    }

    private Person getPerson() {
        return addsWorkToPerson(personName, getWorkToBeAdded());
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

    private void asksAboutWorkToBeAdded() {
        writer.writeLine("What work will be added?");
        this.workToBeAdded = reader.readLine();
    }

    private String getWorkToBeAdded() {
        return workToBeAdded;
    }

    private String getPersonName() {
        return personName;
    }

    private void setPersonName() {
        this.personName = ValidationCommands.asksWhichPerson();
    }
}
