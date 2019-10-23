package tests.functionals;

import core.FunctionalsRepositoryImpl;
import enums.Severity;
import enums.Size;
import functionals.models.BoardImpl;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;
import workitems.contracts.Feedback;
import workitems.contracts.Story;
import workitems.models.BugImpl;
import workitems.models.FeedbackImpl;
import workitems.models.StoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static functionals.models.BoardImpl.getBoardsActivity;

public class TeamsImplTests {
    private TeamsImpl testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private Story story;
    private Bug bug;
    private List<String> steps;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new TeamsImpl("bord1");
        story = new StoryImpl("name12345123131", "asjdjkasdjasijdiadijiaj", Size.MEDIUM);
        bug = new BugImpl(  "name12345123131", "asjdjkasdjasijdiadijiaj", Severity.CRITICAL, steps);
    }

    @Test
    public void check_If_TeamIsCreatedWhenInputIsValid() {
        // Arrange
        TeamsImpl team = new TeamsImpl("Team");

        //Act
        functionalsRepository.addTeam("Team", team);

        //Assert
        Assert.assertEquals(1, functionalsRepository.getTeams().size());
    }

    @Test
    public void check_If_MemberIsAddedToTeam() {
        // Arrange
        TeamsImpl team = new TeamsImpl("Team");
        PersonImpl person = new PersonImpl("Person");

        //Act
        team.addMember(person);

        //Assert
        Assert.assertEquals(1, team.showTeamMembers().size());
    }

    @Test
    public void check_If_MemberIsRemovedFromTeam() {
        // Arrange
        TeamsImpl team = new TeamsImpl("Team");
        MemberImpl member = new MemberImpl("Person",functionalsRepository);

        //Act
        team.addMember(member);
        team.removeMember(member);

        //Assert
        Assert.assertEquals(0, team.showTeamMembers().size());
    }

    @Test
    public void check_If_toStringPrintIsValid() {
        // Arrange
        TeamsImpl team = new TeamsImpl("team");

        //Assert
        String str = "Team teamName: team\n" +
                " Members: []\n" +
                " Boards: Team - team has the following boards:";
        Assert.assertEquals(str, team.toString());
    }
}
