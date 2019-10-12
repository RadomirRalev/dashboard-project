package commands.actions.person;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.Reader;
import core.providers.ConsoleReader;
import functionals.contracts.Person;
import functionals.models.PersonImpl;


import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class CreateNewPerson implements Command {
    private final FunctionalsFactory functionalsFactory;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;

    public CreateNewPerson(FunctionalsFactory functionalsFactory, FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
    }

    @Override
    public String execute(List<String> parameters) {
        String personName = NameJoiner.joinerList(parameters);
        while (checkName(personName)) {
            System.out.printf(PERSON_EXISTS_ERROR_MSG, personName);
            personName = reader.readLine();
            String[] personNameArr = personName.split(" ");
            personName = NameJoiner.joinerArr(personNameArr);
            if (personName.equalsIgnoreCase("cancel")) {
                return TYPE_ANOTHER_COMMAND;
            }
        }
        PersonImpl.getMembersActivity().put(personName, new ArrayList<>());
        return createPerson(personName);
    }

    private String createPerson(String name) {
        Person person = functionalsFactory.createPerson(name);
        functionalsRepository.addPerson(name, person);
        String activity = String.format(PERSON_CREATED_MSG, name);
        person.addActivity(activity);
        return activity;
    }

    private boolean checkName(String personName) {
        return functionalsRepository.getPersons().containsKey(personName);
    }
}