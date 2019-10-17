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
        setName();
        ValidationCommands.checkIfTeamExists(getName(), functionalsRepository);
        return showActivity(getName());
    }

    private String showActivity(String teamActivityHistory) {
        return String.valueOf(getTeamsActivity().get(teamActivityHistory).stream()
                .collect( Collectors.joining( "\n" ) ));
    }

    private void setName() {
        this.teamName = asksWhat("team");
    }
}
