package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.Reader;
import core.providers.ConsoleReader;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class UnassignWorkFromPerson implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;

    public UnassignWorkFromPerson(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
        String personName = parameters.get(0);
        int workToBeUnassigned = Integer.parseInt(reader.readLine());
        if (!functionalsRepository.getPersons().containsKey(personName)) {
            return String.format(PERSON_DOES_NOT_EXIST_ERROR_MSG, personName);
        }
        Person person = functionalsRepository.getPersons().get(personName);
        if (workToBeUnassigned > person.listAssignedWork().size()) {
            return String.format(WORK_NOT_EXIST_MSG, workToBeUnassigned);
        }
        person.unassignWork(workToBeUnassigned - 1);
        return String.format(WORK_UNASSIGNED, workToBeUnassigned, personName);
    }
}