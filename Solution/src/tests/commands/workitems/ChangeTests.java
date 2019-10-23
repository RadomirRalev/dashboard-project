package tests.commands.workitems;

import commands.actions.workitem.Change.ChangeRating;
import commands.actions.workitem.ListWorkItems.ListAllWorkItemsByStatusAndAsignee;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new ChangeRating(functionalsRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_PassedMoreArguments() {
        //Arrange
        List<String> testList = new ArrayList<>();
        testList.add("test");

        //Act and Assert
        testCommand.execute(testList);
    }
}
