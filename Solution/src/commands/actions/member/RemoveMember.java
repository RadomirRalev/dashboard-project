package commands.actions.member;

import commands.actions.ValidationCommands;
import commands.actions.person.NameJoiner;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.models.MemberImpl;
import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class RemoveMember implements Command {
    //TODO Complete development of RemoveMember class
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;

    public RemoveMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute(List<String> parameters) {
        checkArgumentsNumber(parameters);
        writer.writeLine(WHICH_MEMBER);
        String memberName = asksWhichMember();
        memberName = ValidationCommands.checkIfMemberExists(memberName, functionalsRepository);
        if (typeAnotherCommand(memberName)) return TYPE_ANOTHER_COMMAND;
        ArrayList<String> str = getTheTeamsOfTheMember(memberName);
        return removeMember(memberName, str);
    }

    private String removeMember(String memberName, ArrayList<String> str) {
        System.out.println("From which team do you wish to remove this member?");
        String teamToRemoveMemberFrom = reader.readLine();
        if (!str.contains(teamToRemoveMemberFrom)) {
            System.out.println("%s is not a member of this team!");
        } else {
            MemberImpl member = functionalsRepository.getMembers().get(memberName);
            functionalsRepository.getTeams().get(teamToRemoveMemberFrom).removeMember(member);
        }
        return "%s was removed!";
    }

    public ArrayList<String> getTheTeamsOfTheMember(String memberName) {
        System.out.println("%s is a member of: ");
        MemberImpl memberN = functionalsRepository.getMembers().get(memberName);
        ArrayList<String> str = new ArrayList<>();
        functionalsRepository.getTeams().forEach((k, v) -> {
            if (functionalsRepository.getTeams().get(k).showTeamMembers().contains(memberN)) {
                str.add(functionalsRepository.getTeams().get(k).getName());
            }
        });
        System.out.println(str.toString());
        return str;
    }

    private boolean typeAnotherCommand(String memberName) {
        return memberName.equalsIgnoreCase("cancel");
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
