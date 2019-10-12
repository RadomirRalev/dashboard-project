package commands.actions.person;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.*;

public class ShowPerson implements Command {

    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public ShowPerson(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        writer.writeLine(WHICH_PERSON);
        String personName = asksWhichPerson();
        while (checkIfPersonExists(personName)) {
            System.out.printf(PERSON_DOES_NOT_EXIST_MSG, personName);
            personName = asksWhichPerson();
            if (personName.equalsIgnoreCase("cancel")) {
                return TYPE_ANOTHER_COMMAND;
            }
        }
        return showPerson(personName);
    }

    private boolean checkIfPersonExists(String personName) {
        return !functionalsRepository.getPersons().containsKey(personName);
    }

    private String showPerson(String personName) {
        Person person = functionalsRepository.getPersons().get(personName);
        return person.toString();
    }

    private String asksWhichPerson() {
        String[] personName = reader.readLine().split(" ");
        return NameJoiner.joinerArr(personName);
    }
}
