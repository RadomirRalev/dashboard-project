package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.models.MemberImpl;
import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;

class PersonActivityHistory extends ConsoleInteraction {
    private final FunctionalsRepositoryImpl functionalsRepository;

    PersonActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    String execute() {
        personName = asksAboutPersonName();
        personName = ValidationCommands.checkIfMemberExists(personName, functionalsRepository);
        if (isCancel(personName)) {
            return TYPE_ANOTHER_COMMAND;
        }
        return showActivity();
    }

    private String showActivity() {
        MemberImpl member = functionalsRepository.getMembers().get(personName);
        if (member != null) {
            return member.showActivity(personName);
        }
        Person person = functionalsRepository.getPersons().get(personName);
        return person.showActivity(personName);
    }
}
