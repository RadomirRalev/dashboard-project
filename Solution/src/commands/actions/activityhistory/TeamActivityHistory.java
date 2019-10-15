package commands.actions.activityhistory;
import commands.actions.ValidationCommands;
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
    private String teamName;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public TeamActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    public String execute() throws Exception {
        writer.writeLine(WHICH_TEAM);
        teamName = asksWhichTeam();
        teamName = ValidationCommands.checkIfTeamExists(teamName, functionalsRepository);
        return showActivity(teamName);
    }

    private String showActivity(String teamActivityHistory) {
        return String.valueOf(getTeamsActivity().get(teamActivityHistory).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    private String asksWhichTeam() {
        return teamName = reader.readLine();
    }
}
