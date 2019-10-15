package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.models.MemberImpl;

public class PersonActivityHistory {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public PersonActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
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
}