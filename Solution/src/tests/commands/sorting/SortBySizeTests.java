package tests.commands.sorting;

import commands.actions.workitem.ListWorkItems.SortAllStoriesBySize;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import enums.Size;
import enums.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Story;

import workitems.models.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class SortBySizeTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private Story story;
    private Story story2;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new SortAllStoriesBySize(functionalsRepository);
        story = new StoryImpl("story12345", "askldkadslkad", Size.SMALL);
        story2 = new StoryImpl("12345story", "askldkadslkad", Size.LARGE);
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
    public void sortBySize_should_showSortedList_when_called() {
        //Arange
        List<String> testList = new ArrayList<>();
        functionalsRepository.addWorkItem(story.getId(), story);
        functionalsRepository.addWorkItem(story2.getId(), story2);
        story.setStatus(Status.DONE);
        story2.setStatus(Status.DONE);
        StringBuilder str = new StringBuilder();
        str.append(String.format("Story with ID:%d\n", story.getId()));
        str.append("Title: story12345\n");
        str.append("Description: askldkadslkad\n");
        str.append("Status: Done\n");
        str.append("Priority: NotAssigned\n");
        str.append("Size: Small\n");
        str.append("\n");
        str.append(String.format("Story with ID:%d\n", story2.getId()));
        str.append("Title: 12345story\n");
        str.append("Description: askldkadslkad\n");
        str.append("Status: Done\n");
        str.append("Priority: NotAssigned\n");
        str.append("Size: Large");
        //Act and Assert
        Assert.assertEquals(str.toString(), testCommand.execute(testList));
    }
}
