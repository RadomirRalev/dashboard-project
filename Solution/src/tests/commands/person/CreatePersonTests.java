package tests.commands.person;

import commands.actions.CommandsConstants;
import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.actions.person.CreateNewPerson;
import commands.actions.person.NameJoiner;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.contracts.Writer;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
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

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static commands.actions.CommandsConstants.WHAT;

public class CreatePersonTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateNewPerson(functionalsFactory, functionalsRepository);
        reader = new ConsoleReader();
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
    public void check_If_PersonCreatedWhenInputIsValid() {
        // Arrange
        Person person = new PersonImpl("Name");

        //Act
        functionalsRepository.addPerson("Name", person);

        //Assert
        Assert.assertEquals(1, functionalsRepository.getPersons().size());
    }
}
