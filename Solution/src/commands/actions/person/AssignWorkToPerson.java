package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;

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
        String workToBeAdded = asksAboutWorkToBeAdded();
        if (checkIfPersonExists(personName)) return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        Person person = addsWorkToPerson(personName, workToBeAdded);
        return addsWorkToActivityHistory(personName, workToBeAdded, person);
    }

    private String addsWorkToActivityHistory(String personName, String workToBeAdded, Person person) {
        String activity = String.format(WORK_ADDED_MSG, workToBeAdded, personName);
        person.addActivity(activity);
        return activity;
    }

    private Person addsWorkToPerson(String personName, String workToBeAdded) {
        Person person = functionalsRepository.getPersons().get(personName);
        person.assignWork(workToBeAdded);
        return person;
    }

    private boolean checkIfPersonExists(String personName) {
        return !functionalsRepository.getPersons().containsKey(personName);
    }

    private String asksAboutWorkToBeAdded() {
        writer.writeLine("What work will be added?");
        return reader.readLine();
    }
}
