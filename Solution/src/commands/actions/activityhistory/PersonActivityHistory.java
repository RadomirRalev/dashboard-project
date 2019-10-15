package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
import commands.actions.person.NameJoiner;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.models.MemberImpl;
import static commands.actions.CommandsConstants.*;

public class PersonActivityHistory {
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public PersonActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute() throws Exception {
        writer.writeLine(WHICH_PERSON);
        String personName = asksWhichPerson();
        personName = ValidationCommands.checkIfMemberExists(personName, functionalsRepository);
        return showActivity(personName);
    }

    private String showActivity(String personName) {
        MemberImpl member = functionalsRepository.getMembers().get(personName);
        if (member != null) {
            return member.showActivity(personName);
        }
        Person person = functionalsRepository.getPersons().get(personName);
        return person.showActivity(personName);
    }

    private String asksWhichPerson() {
        String[] personName = reader.readLine().split(" ");
        return NameJoiner.joinerArr(personName);
    }
}