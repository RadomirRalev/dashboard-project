package tests.commands.sorting;

import commands.actions.workitem.ListWorkItems.SortAllBugsBySeverity;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import enums.Priority;
import enums.Severity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;
import workitems.models.BugImpl;

import java.util.ArrayList;
import java.util.List;

public class SortBySeverityTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private List<String> steps;
    private Bug bug;
    private Bug bug2;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new SortAllBugsBySeverity(functionalsRepository);
        steps = new ArrayList<>();
        steps.add("baluche1");
        steps.add("Baluche2");
        bug = new BugImpl("bugche12345", "askdjaksjakjddkj", Severity.MAJOR, steps);
        bug2 = new BugImpl("12345bugche", "askdjaksjakjddkj", Severity.MINOR, steps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_should_throwException_when_PassedMoreArguments() {
        //Arrange
        List<String> testList = new ArrayList<>();
        testList.add("test");

        //Act and Assert
        testCommand.execute(testList);
    }

    @Test
    public void sortBySeverity_should_showSortedList_when_called() {
        //Arange
        List<String> testList = new ArrayList<>();
        functionalsRepository.addWorkItem(bug.getId(), bug);
        functionalsRepository.addWorkItem(bug2.getId(), bug2);
        bug.setPriority(Priority.HIGH);
        StringBuilder str = new StringBuilder();
        str.append(String.format("Bug with ID:%d\n", bug2.getId()));
        str.append("Title: 12345bugche\n");
        str.append("Description: askdjaksjakjddkj\n");
        str.append("Status: Active\n");
        str.append("Priority: NotAssigned\n");
        str.append("Severity: Minor\n");
        str.append("Steps needed to reproduce the bug: [baluche1, Baluche2]\n");
        str.append("\n");
        str.append(String.format("Bug with ID:%d\n", bug.getId()));
        str.append("Title: bugche12345\n");
        str.append("Description: askdjaksjakjddkj\n");
        str.append("Status: Active\n");
        str.append("Priority: High\n");
        str.append("Severity: Major\n");
        str.append("Steps needed to reproduce the bug: [baluche1, Baluche2]");
        //Act and Assert
        Assert.assertEquals(str.toString(), testCommand.execute(testList));
    }
}
