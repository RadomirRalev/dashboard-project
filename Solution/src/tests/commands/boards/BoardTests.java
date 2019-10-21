package tests.commands.boards;

import commands.actions.board.CreateBoard;
import commands.actions.person.CreateNewPerson;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateBoard(functionalsFactory, functionalsRepository);
        reader = new ConsoleReader();
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_passedZeroArguments() {
        // Arrange
        Team team = new TeamsImpl("X");
        functionalsRepository.getTeams().put("X", team);
        List<String> testList = new ArrayList<>();

        // Act & Assert
        testCommand.execute(testList);
    }

}
