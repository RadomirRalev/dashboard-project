package commands.actions.person;

import commands.actions.ConsoleInteraction;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static commands.actions.CommandsConstants.*;

public class ListPersons extends ConsoleInteraction implements Command{
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ListPersons(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        return prepareListOfPersons();
    }

    private String prepareListOfPersons() {
        StringJoiner str = new StringJoiner(", ");
        Set<String> keys = functionalsRepository.getPersons().keySet();
        keys.forEach (e -> str.add(e) );
        String personsList = str.toString(); //in alphabetical order
        return listPersons(personsList);
    }

    private String listPersons(String personsList) {

        if (functionalsRepository.getPersons().size() == 0) {
            return PERSONSLIST_IS_EMPTY;
        }
        return String.format(PERSONSLIST_INCLUDES, personsList);
    }
}
