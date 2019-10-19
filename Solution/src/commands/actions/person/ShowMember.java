package commands.actions.person;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;

import java.util.List;

import static commands.actions.CommandsConstants.TYPE_ANOTHER_COMMAND;

public class ShowMember extends ConsoleInteraction implements Command {

    private final FunctionalsRepositoryImpl functionalsRepository;

    public ShowMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) throws Exception {
        ConsoleInteraction.validateInput(parameters.size());
        personName = asksAboutPersonName();
        personName = ValidationCommands.checkIfPersonExists(personName, functionalsRepository);
        if (isCancel(personName)) return TYPE_ANOTHER_COMMAND;
        return showMember(personName);
    }

    private String showMember(String memberName) {
        Person member = functionalsRepository.getMembers().get(memberName);
        return member.toString();
    }
}

