package commands.actions.person;
import commands.actions.ConsoleInteraction;
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

public class UnassignWorkFromPerson extends ConsoleInteraction implements Command {
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
        ConsoleInteraction.validateInput(parameters.size());
        personName = asksAboutPersonName();
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        if (isCancel(personName)) {
            return TYPE_ANOTHER_COMMAND;
        }
        Person person = getPerson();
        assignedWorkListing(person);
        workToBeUnassigned = asksAboutWorkToBeUnassigned();
        if (ValidationCommands.checkIfWorkExists(workToBeUnassigned, person)) {
            return String.format(WORK_NOT_EXIST_MSG, workToBeUnassigned);
        }
        person.unassignWork(workToBeUnassigned - 1);
        return addUnassignWorkToActivityHistory(personName, workToBeUnassigned, person);
    }

    private void assignedWorkListing(Person person) {
        System.out.printf(LIST_ASSIGNEDWORK, personName, NameJoiner.joinerList(person.getAssignedWork()));
    }

    private Person getPerson() {
        return functionalsRepository.getPersons().get(personName);
    }

    private String addUnassignWorkToActivityHistory(String personName, int workToBeUnassigned, Person person) {
        String activity = String.format(WORK_UNASSIGNED, workToBeUnassigned, personName);
        PersonImpl.addActivity(activity, personName);
        return activity;
    }

    private int asksAboutWorkToBeUnassigned() {
        writer.writeLine("\n" + "===========" + "\n" + NUMBER_ASSIGNEDWORK);
        return Integer.parseInt(reader.readLine());
    }
}
