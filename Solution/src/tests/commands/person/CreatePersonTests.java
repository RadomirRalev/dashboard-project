package tests.commands.person;

import commands.actions.person.CreateNewPerson;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.factories.FunctionalsFactoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreatePersonTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateNewPerson(functionalsFactory, functionalsRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedMoreThanZeroArguments() throws Exception {
        // Arrange
        List<String> testList = new ArrayList<>();
        testList.add("teamName");
        testList.add("number");

        // Act & Assert
        testCommand.execute(testList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_inputStringEmpty() throws Exception {
        // Arrange
        List<String> testList = new ArrayList<>();
        String personName = "";

        // Act
        testCommand.execute(testList);

        //Assert

    }

}
