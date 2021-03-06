package tests.commands.sorting;

import commands.actions.workitem.ListWorkItems.SortAllWorkItemsByTitle;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import enums.Severity;
import enums.Size;
import enums.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;
import workitems.contracts.Feedback;
import workitems.contracts.Story;
import workitems.models.BugImpl;
import workitems.models.FeedbackImpl;
import workitems.models.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class SortByTitleTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private Feedback feedback1;
    private List<String> steps;
    private Bug bug;
    private Story story;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new SortAllWorkItemsByTitle(functionalsRepository);
        steps = new ArrayList<>();
        steps.add("baluche1");
        steps.add("Baluche2");
        bug = new BugImpl("bugche12345", "askdjaksjakjddkj", Severity.MAJOR, steps);
        story = new StoryImpl("story12345", "askldkadslkad", Size.LARGE);
        feedback1 = new FeedbackImpl("feedback123", "lsadklasdklkdkdl", 5);
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
    public void sortByTitle_should_showSortedList_when_called() {
        //Arange
        List<String> testList = new ArrayList<>();
        functionalsRepository.addWorkItem(bug.getId(), bug);
        functionalsRepository.addWorkItem(story.getId(), story);
        functionalsRepository.addWorkItem(feedback1.getId(), feedback1);
        bug.setStatus(Status.ACTIVE);
        story.setStatus(Status.DONE);
        StringBuilder str = new StringBuilder();
        str.append(String.format("Bug with ID:%d\n", bug.getId()));
        str.append("Title: bugche12345\n");
        str.append("Description: askdjaksjakjddkj\n");
        str.append("Status: Active\n");
        str.append("Priority: NotAssigned\n");
        str.append("Severity: Major\n");
        str.append("Steps needed to reproduce the bug: [baluche1, Baluche2]\n");
        str.append("\n");
        str.append(String.format("Feedback with ID:%d\n", feedback1.getId()));
        str.append("Title: feedback123\n");
        str.append("Description: lsadklasdklkdkdl\n");
        str.append("Status: New\n");
        str.append("Rating: 5\n");
        str.append("\n");
        str.append(String.format("Story with ID:%d\n", story.getId()));
        str.append("Title: story12345\n");
        str.append("Description: askldkadslkad\n");
        str.append("Status: Done\n");
        str.append("Priority: NotAssigned\n");
        str.append("Size: Large");
        //Act and Assert
        Assert.assertEquals(str.toString(), testCommand.execute(testList));
    }
}
