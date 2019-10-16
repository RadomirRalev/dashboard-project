package commands.actions.activityhistory;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import core.FunctionalsRepositoryImpl;
import java.util.stream.Collectors;
import static functionals.models.TeamsImpl.getTeamsActivity;

public class TeamActivityHistory extends ConsoleInteraction {
    private String teamName;
    private final FunctionalsRepositoryImpl functionalsRepository;


    public TeamActivityHistory(FunctionalsRepositoryImpl functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
    }

    public String execute() throws Exception {
        setTeamName();
        ValidationCommands.checkIfTeamExists(getTeamName(), functionalsRepository);
        return showActivity(getTeamName());
    }

    private String showActivity(String teamActivityHistory) {
        return String.valueOf(getTeamsActivity().get(teamActivityHistory).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    private String getTeamName() {
        return teamName;
    }

    private void setTeamName() {
        this.teamName = asksWhich("team");
    }
}
