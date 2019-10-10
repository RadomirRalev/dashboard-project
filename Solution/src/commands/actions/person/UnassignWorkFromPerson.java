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
        if (checkIfPersonExists(personName)) return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        int workToBeUnassigned = Integer.parseInt(reader.readLine());
        Person person = functionalsRepository.getPersons().get(personName);
        if (checkIfWorkExists(workToBeUnassigned, person)) return String.format(WORK_NOT_EXIST_MSG, workToBeUnassigned);
        person.unassignWork(workToBeUnassigned - 1);
        return addUnassignWorkToActivityHistory(personName, workToBeUnassigned, person);
    }

    private String addUnassignWorkToActivityHistory(String personName, int workToBeUnassigned, Person person) {
        String activity = String.format(WORK_UNASSIGNED, workToBeUnassigned, personName);
        person.addActivity(activity);
        return activity;
    }

    private boolean checkIfWorkExists(int workToBeUnassigned, Person person) {
        return workToBeUnassigned > person.listAssignedWork().size();
    }

    private boolean checkIfPersonExists(String personName) {
        if (!functionalsRepository.getPersons().containsKey(personName)) {
            return true;
        }
        writer.writeLine("What number of assigned work to be removed?");
        return false;
    }
}
