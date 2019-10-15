package commands.actions.member;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import java.util.List;

public class ShowMember implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private String memberName;

    public ShowMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        setMemberName();
        ValidationCommands.checkIfPersonExists(getMemberName(), functionalsRepository);
        return showMember(memberName);
    }

    private String showMember(String memberName) {
        Person member = functionalsRepository.getMembers().get(memberName);
        return member.toString();
    }

    private String getMemberName() {
        return memberName;
    }

    private void setMemberName() {
        this.memberName = ValidationCommands.asksWhichPerson();
    }
}
