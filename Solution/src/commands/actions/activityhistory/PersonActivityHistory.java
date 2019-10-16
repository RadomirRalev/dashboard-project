package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.models.MemberImpl;

public class PersonActivityHistory extends ConsoleInteraction {
    private final FunctionalsRepositoryImpl functionalsRepository;

    public PersonActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    public String execute() throws Exception {
        setPersonName();
        ValidationCommands.checkIfMemberExists(getPersonName(), functionalsRepository);
        return showActivity();
    }

    private String showActivity() {
        MemberImpl member = functionalsRepository.getMembers().get(getPersonName());
        if (member != null) {
            return member.showActivity(getPersonName());
        }
        Person person = functionalsRepository.getPersons().get(getPersonName());
        return person.showActivity(getPersonName());
    }
}