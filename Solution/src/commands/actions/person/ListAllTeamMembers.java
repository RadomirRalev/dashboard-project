package commands.actions.person;

import commands.actions.ConsoleInteraction;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.*;

import static commands.actions.CommandsConstants.*;

public class ListAllTeamMembers extends ConsoleInteraction implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ListAllTeamMembers(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        return membersList(prepareMembersList().toString());
    }

    private StringJoiner prepareMembersList() {
        StringJoiner str = new StringJoiner(", ");
        Set<String> keys = functionalsRepository.getMembers().keySet();
        keys.forEach (e -> str.add(e) );
        return str;
    }

    private String membersList(String membersList) {
        if (functionalsRepository.getMembers().size() == 0) {
            return MEMBERSLIST_IS_EMPTY;
        }
        return String.format(MEMBERSLIST_INCLUDES, membersList);
    }
}
