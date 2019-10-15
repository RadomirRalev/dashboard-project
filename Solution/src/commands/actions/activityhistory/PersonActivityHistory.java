package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
import commands.actions.person.NameJoiner;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.providers.ConsoleReader;
import functionals.contracts.Person;
import functionals.models.MemberImpl;

public class PersonActivityHistory {
    private Reader reader;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public PersonActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
    }

    public String execute() throws Exception {
        String personName = ValidationCommands.asksWhichPerson();
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