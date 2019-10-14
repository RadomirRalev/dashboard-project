package commands.actions.member;

import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

import java.util.*;

import static commands.actions.CommandsConstants.*;

public class ListAllTeamMembers implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public ListAllTeamMembers(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        StringJoiner str = prepareMembersList();
        return membersList(str.toString());
    }

    private StringJoiner prepareMembersList() {
        StringJoiner str = new StringJoiner(", ");
        Set<String> keys = functionalsRepository.getPersons().keySet();
        keys.forEach (e -> str.add(e) );
        return str;
    }

    private String membersList(String membersList) {
        if (functionalsRepository.getMembers().size() == 0) {
            return PERSONSLIST_IS_EMPTY;
        }
        return String.format(MEMBERSLIST_INCLUDES, membersList);
    }
}
