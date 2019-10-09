package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.models.PersonImpl;

import java.util.List;
import java.util.StringJoiner;

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
        StringJoiner str = new StringJoiner(" ");
        for (String parameter : parameters) {
            str.add(parameter);
        }
        String personName = str.toString();
        writer.writeLine("What work will be added?");
        String workToBeAdded = reader.readLine();
        if (!functionalsRepository.getPersons().containsKey(personName)) {
            return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        }
        Person person = functionalsRepository.getPersons().get(personName);
        person.assignWork(workToBeAdded);

        return String.format(WORK_ADDED_MSG, workToBeAdded, personName);
    }
 }
