package tests.commands.sorting;

import commands.actions.workitem.ListWorkItems.ListAllWorkItems;
import commands.actions.workitem.ListWorkItems.SortAllWorkItemsByPriority;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import enums.Priority;
import enums.Severity;
import enums.Size;
import functionals.contracts.Person;
import functionals.models.PersonImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;
import workitems.contracts.Story;
import workitems.models.BugImpl;
import workitems.models.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ListAllWorkItemsTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private List<String> steps;
    private Bug bug;
    private Story story;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new ListAllWorkItems(functionalsRepository);
        steps = new ArrayList<>();
        steps.add("baluche1");
        steps.add("Baluche2");
        story = new StoryImpl("story12345", "askldkadslkad", Size.LARGE);
        bug = new BugImpl("bugche12345", "askdjaksjakjddkj", Severity.MAJOR, steps);
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
    public void listAllWorkItems_should_listAllWorkItems_when_called() {
        //Arange
        List<String> testList = new ArrayList<>();
        functionalsRepository.addWorkItem(bug.getId(), bug);
        functionalsRepository.addWorkItem(story.getId(), story);
        bug.setPriority(Priority.HIGH);
        story.setPriority(Priority.LOW);
        StringBuilder str = new StringBuilder();
        str.append(String.format("Story with ID:%d\n", story.getId()));
        str.append("Title: story12345\n");
        str.append("Description: askldkadslkad\n");
        str.append("Status: NotDone\n");
        str.append("Priority: Low\n");
        str.append("Size: Large\n");
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
