package tests.commands.person;

import commands.actions.person.AssignWorkToPerson;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.factories.FunctionalsFactoryImpl;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.BoardImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;

import java.util.ArrayList;
import java.util.List;

public class AssignWorkToPersonTest {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private FunctionalsFactoryImpl functionalsFactory;


    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        functionalsFactory = new FunctionalsFactoryImpl();
        testCommand = new AssignWorkToPerson(functionalsRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreThanZeroArguments() {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("name");
        testList.add("number");

        // Act & Assert
        testCommand.execute(testList);
    }

    @Test
    public void check_If_WorkIsAddedToPersonWhenInputIsValid() {
        // Arrange
        Person person = new PersonImpl("Sample Name");
        functionalsRepository.getPersons().put("Sample Name", person);
        Team team = new TeamsImpl("X");
        functionalsRepository.getTeams().put("X", team);
        Board bord1 = new BoardImpl("bord1");
        functionalsRepository.addBoard("bord1", bord1);

        //Act
        team.addBoard(bord1);
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Step 1");
        steps.add("Step 2");
        Bug bug = functionalsFactory.createBug("This is the name of the bug work item",
                "This is the description of the work item bug",
                "critical", steps);
        functionalsRepository.getBoards().get("bord1").addWorkItems(bug);
        person.addWorkItems(bug);

        //Assert
        Assert.assertEquals(1, person.listWorkItems().size());
    }
}
