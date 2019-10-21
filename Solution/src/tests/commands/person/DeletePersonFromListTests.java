package tests.commands.person;

import commands.actions.person.DeletePersonFromList;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeletePersonFromListTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new DeletePersonFromList(functionalsRepository);
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
    public void check_If_PersonIsAddedToTeamWhenInputIsValid() {
        // Arrange
        List<String> testList = new ArrayList<>();
        Person person = new PersonImpl("Name");
        functionalsRepository.addPerson("Name", person);

        //Act
        functionalsRepository.deletePerson("Name");

        //Assert
        Assert.assertEquals(0, functionalsRepository.getPersons().size());

    }
}
