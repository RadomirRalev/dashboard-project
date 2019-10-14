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

    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        writer.writeLine(WHICH_MEMBER);
        String memberName = asksWhichMember();
        memberName = ValidationCommands.checkIfMemberExists(memberName, functionalsRepository);
        ArrayList<String> str = getTheTeamsOfTheMember(memberName);
        return removeMember(memberName, str);
    }

    private String removeMember(String memberName, ArrayList<String> str) throws Exception {
        writer.writeLine(WHICH_TEAM);
        String teamToRemoveMemberFrom = reader.readLine();
        ValidationCommands.checkIfMemberOfTeam(memberName, teamToRemoveMemberFrom, str);
        MemberImpl member = functionalsRepository.getMembers().get(memberName);
        functionalsRepository.getTeams().get(teamToRemoveMemberFrom).removeMember(member);
        return String.format(MEMBER_REMOVED_FROM_TEAM, memberName, teamToRemoveMemberFrom);
    }

    private ArrayList<String> getTheTeamsOfTheMember(String memberName) {
        System.out.printf(MEMBER_OF_TEAMS, memberName);
        MemberImpl memberN = functionalsRepository.getMembers().get(memberName);
        ArrayList<String> str = new ArrayList<>();
        functionalsRepository.getTeams().forEach((k, v) -> {
            if (functionalsRepository.getTeams().get(k).showTeamMembers().contains(memberN)) {
                str.add(functionalsRepository.getTeams().get(k).getName());
            }
        });
        System.out.println(str.toString(). replace("[", "").replace("]", ""));
        return str;
    }

    private String asksWhichMember() {
        String[] activityHistoryOfMember = reader.readLine().split(" ");
        return NameJoiner.joinerArr(activityHistoryOfMember);
    }
}
