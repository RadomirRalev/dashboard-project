package tests.commands.person;

import commands.actions.person.AssignWorkToPerson;
import commands.actions.person.ListPersons;
import commands.actions.person.NameJoiner;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.models.PersonImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListPersonsTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new ListPersons(functionalsRepository);
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
}
