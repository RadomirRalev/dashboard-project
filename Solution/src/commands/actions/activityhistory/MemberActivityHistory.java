package commands.actions.activityhistory;

import commands.actions.person.NameJoiner;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.models.MemberImpl;

import static commands.actions.CommandsConstants.*;

public class MemberActivityHistory {
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public MemberActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute() {
        writer.writeLine(WHICH_MEMBER);
        String memberActivityHistory = asksWhichMember();
        while (!checkIfMemberExists(memberActivityHistory)) {
            System.out.printf(MEMBER_DOES_NOT_EXIST_MSG, memberActivityHistory);
            memberActivityHistory = asksWhichMember();
            if (memberActivityHistory.equalsIgnoreCase("cancel")) {
                return TYPE_ANOTHER_COMMAND;
            }
        }
        return showActivity(memberActivityHistory);
    }

    private String showActivity(String memberActivityHistory) {
        MemberImpl member = functionalsRepository.getMembers().get(memberActivityHistory);
        return member.showActivity(memberActivityHistory);
    }

    private String asksWhichMember() {
        String[] activityHistoryOfMember = reader.readLine().split(" ");
        return NameJoiner.joinerArr(activityHistoryOfMember);
    }

    private boolean checkIfMemberExists(String activityHistoryOfMember) {
        return functionalsRepository.getMembers().containsKey(activityHistoryOfMember);
    }
}
