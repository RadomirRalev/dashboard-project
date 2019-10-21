package tests.commands.person;

import commands.actions.person.CreateNewPerson;
import commands.actions.person.NameJoiner;
import core.FunctionalsRepositoryImpl;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;
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
import workitems.contracts.WorkItems;

import java.util.ArrayList;
import java.util.List;

public class NameJoinerTests {

    private FunctionalsRepository functionalsRepository;
    private FunctionalsFactory functionalsFactory;


    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        functionalsFactory = new FunctionalsFactoryImpl();
    }

    @Test
    public void nameJoinerOfArray_should_joinNamesAndCapitalizeThem() {
        // Arrange
        String[] personNameArr = {"radomir", "ralev"};

        // Act & Assert
        String check = NameJoiner.joinerArr(personNameArr);

        //Assert
        Assert.assertEquals("Radomir Ralev", check);
    }

    @Test
    public void nameJoinerOfList_should_produceStringStartingWithWorkItemNumber() {
        // Arrange
        Person person = new PersonImpl("Name");
        functionalsRepository.getPersons().put("Name", person);
        Team team = new TeamsImpl("X");
        functionalsRepository.getTeams().put("X", team);
        Board bord1 = new BoardImpl("bord1");
        functionalsRepository.addBoard("bord1", bord1);
        team.addBoard(bord1);
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Step 1");
        steps.add("Step 2");
        Bug bug = functionalsFactory.createBug("This is the name of the bug work item",
                "This is the description of the work item bug",
                "critical", steps);
        functionalsRepository.getBoards().get("bord1").addWorkItems(bug);
        person.addWorkItems(bug);
        List<WorkItems> parameters = person.getAssignedWork();

        // Act
        String check = NameJoiner.joinerList(parameters);

        //Assert
        Assert.assertTrue(check.startsWith("Work item number: "));
    }
}
