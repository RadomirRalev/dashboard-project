package commands.actions.member;
import commands.actions.ValidationCommands;
import commands.actions.person.NameJoiner;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import java.util.List;
import static commands.actions.CommandsConstants.*;
import static commands.actions.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;

public class ShowMember implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public ShowMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) {
        checkArgumentsNumber(parameters);
        writer.writeLine(WHICH_MEMBER);
        String memberName = asksWhichMember();
        memberName = ValidationCommands.checkIfMemberExists(memberName, functionalsRepository);
        if (typeAnotherCommand(memberName)) return TYPE_ANOTHER_COMMAND;
        return showMember(memberName);
    }

    private boolean typeAnotherCommand(String memberName) {
        return memberName.equalsIgnoreCase("cancel");
    }

    private String showMember(String memberName) {
        Person member = functionalsRepository.getMembers().get(memberName);
        return member.toString();
    }

    private String asksWhichMember() {
        String[] activityHistoryOfMember = reader.readLine().split(" ");
        return NameJoiner.joinerArr(activityHistoryOfMember);
    }

    private void checkArgumentsNumber(List<String> parameters) {
        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
    }
}
