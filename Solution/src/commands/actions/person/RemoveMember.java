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

public class RemoveMember extends PersonName implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 0;
    private final FunctionalsRepositoryImpl functionalsRepository;
    private Reader reader;
    private Writer writer;
    private String memberName;


    public RemoveMember(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute(List<String> parameters) throws Exception {
        ValidationCommands.validateInput(parameters, CORRECT_NUMBER_OF_ARGUMENTS);
        setPersonName();
        ValidationCommands.checkIfPersonExists(getPersonName(), functionalsRepository);
        return removeMember(getPersonName(), getTheTeamsOfTheMember(getPersonName()));
    }

    private String removeMember(String memberName, ArrayList<String> str) throws Exception {
        writer.writeLine(WHICH_TEAM);
        String teamToRemoveMemberFrom = reader.readLine();
        ValidationCommands.checkIfMemberOfTeam(memberName, teamToRemoveMemberFrom, str);
        MemberImpl member = functionalsRepository.getMembers().get(memberName);
        functionalsRepository.getTeams().get(teamToRemoveMemberFrom).removeMember(member);
        addToActivityHistory(teamToRemoveMemberFrom, memberName);
        return String.format(MEMBER_REMOVED_FROM_TEAM, memberName, teamToRemoveMemberFrom);
    }

    private ArrayList<String> getTheTeamsOfTheMember(String memberName) {
        System.out.printf(MEMBER_OF_TEAMS, memberName);
        MemberImpl member = functionalsRepository.getMembers().get(memberName);
        System.out.println(member.getTheTeamsOfTheMember().toString().replace("[", "").replace("]", ""));
        return member.getTheTeamsOfTheMember();
    }

    private String addToActivityHistory(String teamToRemoveMemberFrom, String memberName) {
        String activity = String.format(MEMBER_REMOVED_FROM_TEAM, memberName, teamToRemoveMemberFrom);
        PersonImpl.addActivity(activity, memberName);
        TeamsImpl.addActivity(activity, teamToRemoveMemberFrom);
        return activity;
    }
}
