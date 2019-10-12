package commands.actions.activityhistory;

import commands.actions.person.NameJoiner;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import java.util.stream.Collectors;

import static commands.actions.CommandsConstants.*;
import static functionals.models.TeamsImpl.getTeamsActivity;

public class TeamActivityHistory {
    private Reader reader;
    private Writer writer;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public TeamActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute() {
        writer.writeLine(WHICH_TEAM);
        String teamActivityHistory = asksWhichTeam();
        while (!checkIfTeamExists(teamActivityHistory)) {
            System.out.printf(TEAM_DOES_NOT_EXIST_MSG, teamActivityHistory);
            teamActivityHistory = asksWhichTeam();
            if (teamActivityHistory.equalsIgnoreCase("cancel")) {
                return TYPE_ANOTHER_COMMAND;
            }
        }
        return showActivity(teamActivityHistory);
    }

    private String showActivity(String teamActivityHistory) {
        return String.valueOf(getTeamsActivity().get(teamActivityHistory).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    private String asksWhichTeam() {
        String[] teamActivityHistory = reader.readLine().split(" ");
        return NameJoiner.joinerArr(teamActivityHistory);
    }

    private boolean checkIfTeamExists(String teamActivityHistory) {
        return functionalsRepository.getMembers().containsKey(teamActivityHistory);
    }
}