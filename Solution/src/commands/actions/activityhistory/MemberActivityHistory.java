package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
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

    public String execute() throws Exception {
        writer.writeLine(WHICH_MEMBER);
        String memberName = asksWhichMember();
        memberName = ValidationCommands.checkIfMemberExists(memberName, functionalsRepository);
        if (typeAnotherCommand(memberName)) return TYPE_ANOTHER_COMMAND;
        return showActivity(memberName);
    }

    private boolean typeAnotherCommand(String memberName) {
        return memberName.equalsIgnoreCase("cancel");
    }

    private String showActivity(String memberName) {
        MemberImpl member = functionalsRepository.getMembers().get(memberName);
        return member.showActivity(memberName);
    }

    private String asksWhichMember() {
        String[] activityHistoryOfMember = reader.readLine().split(" ");
        return NameJoiner.joinerArr(activityHistoryOfMember);
    }
}