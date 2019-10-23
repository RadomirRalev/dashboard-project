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
import workitems.contracts.WorkItems;

import java.util.*;

import static commands.actions.CommandsConstants.*;

public class AssignWorkToPerson extends ConsoleInteraction implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;
    private String filterType;
    private String identifier;

    public AssignWorkToPerson(FunctionalsRepositoryImpl functionalsRepository) {
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
        getFilterType();
        if (isCancel(filterType)) {
            return TYPE_ANOTHER_COMMAND;
        }
        printOptions();
        writer.writeLine("Please specify the identifier of the work item: \n");
        identifier = reader.readLine();
        Person person = functionalsRepository.getPersons().get(personName);
        if (person.getAssignedWork().contains(getWorkToBeAdded())) {
            return WORK_ALREADY_ADDED;
        }
        return addsWorkToActivityHistory(personName, getWorkToBeAdded().getTitle(), addsWorkToPerson(personName));
    }

    private String addsWorkToActivityHistory(String personName, String workToBeAdded, Person person) {
        String activity = String.format(WORK_ADDED_MSG, workToBeAdded, personName);
        PersonImpl.addActivity(activity, personName);
        return activity;
    }

    private Person addsWorkToPerson(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        person.addWorkItems(getWorkToBeAdded());
        return person;
    }

    private void getFilterType() {
        writer.writeLine("Please select bug, story or feedback:");
        filterType = reader.readLine();
        filterType = ValidationCommands.checkBugStoryFeedback(filterType);
    }

    private void printOptions() {
        Map<Integer, WorkItems> WS = new HashMap<>();
        functionalsRepository.getWorkItems().entrySet().stream()
                .filter(workitem -> workitem.getValue().getItemType().equalsIgnoreCase(filterType))
                .forEach(element -> WS.put(element.getKey(), element.getValue()));
        for (Integer key : WS.keySet()) {
            WorkItems workItemTitle = WS.get(key);
            writer.writeLine("Identifier: " + key);
            writer.writeLine("Title: " + workItemTitle.getTitle());
            writer.writeLine("===============");
        }
    }

    private WorkItems getWorkToBeAdded() {
        return functionalsRepository.getWorkItems().get(Integer.parseInt(identifier));
    }
}

