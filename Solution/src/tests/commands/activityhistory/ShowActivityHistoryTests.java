package tests.commands.activityhistory;

import commands.actions.activityhistory.ShowActivityHistory;
import commands.actions.person.CreateNewPerson;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowActivityHistoryTests {
    private Command testCommand;

    @Before
    public void before() {
        FunctionalsRepositoryImpl functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new ShowActivityHistory(functionalsRepository);
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
