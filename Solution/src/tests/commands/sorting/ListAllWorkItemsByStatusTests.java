package tests.commands.sorting;

import commands.actions.workitem.ListWorkItems.ListAllWorkItemsByAsignee;
import commands.actions.workitem.ListWorkItems.ListAllWorkItemsByStatus;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListAllWorkItemsByStatusTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new ListAllWorkItemsByStatus(functionalsRepository);
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
