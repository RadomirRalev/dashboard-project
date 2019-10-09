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

import static commands.actions.CommandsConstants.*;

public class AssignWorkToPerson implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public AssignWorkToPerson(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String personName = parameters.get(0);
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