package commands.actions.person;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.*;

public class RemoveMember extends ConsoleInteraction implements Command {
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;


    public RemoveMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());
        memberName = asksAboutPersonName();
        memberName = ValidationCommands.checkIfPersonExists(memberName, functionalsRepository);
        if (isCancel(memberName)) {
            return TYPE_ANOTHER_COMMAND;
        }
        ArrayList<String> str = getTheTeamsOfTheMember(memberName);
        return removeMember(memberName, str);
    }

    private String removeMember(String memberName, ArrayList<String> str) {
        writer.writeLine(WHICH_TEAM);
        String teamToRemoveMemberFrom = reader.readLine();
        ValidationCommands.checkIfMemberOfTeam(memberName, teamToRemoveMemberFrom, str);
        MemberImpl member = functionalsRepository.getMembers().get(memberName);
        functionalsRepository.getTeams().get(teamToRemoveMemberFrom).removeMember(member);
        functionalsRepository.removeMember(memberName);
        addToActivityHistory(teamToRemoveMemberFrom, memberName);
        return String.format(MEMBER_REMOVED_FROM_TEAM, memberName, teamToRemoveMemberFrom);
    }

    private ArrayList<String> getTheTeamsOfTheMember(String memberName) {
        System.out.printf(MEMBER_OF_TEAMS, memberName);
        MemberImpl memberN = functionalsRepository.getMembers().get(memberName);
        ArrayList<String> str = memberN.getTheTeamsOfTheMember();
        System.out.println(str.toString(). replace("[", "").replace("]", ""));
        return str;
    }

    private String addToActivityHistory(String teamToRemoveMemberFrom, String memberName) {
        String activity = String.format(MEMBER_REMOVED_FROM_TEAM, memberName, teamToRemoveMemberFrom);
        PersonImpl.addActivity(activity, memberName);
        TeamsImpl.addActivity(activity, teamToRemoveMemberFrom);
        return activity;
    }
}

