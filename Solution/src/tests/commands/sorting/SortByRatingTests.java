package tests.commands.sorting;

import commands.actions.workitem.ListWorkItems.SortAllFeedbackByRating;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import enums.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Feedback;
import workitems.models.FeedbackImpl;


import java.util.ArrayList;
import java.util.List;

public class SortByRatingTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private Feedback feedback1;
    private Feedback feedback2;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new SortAllFeedbackByRating(functionalsRepository);
        feedback1 = new FeedbackImpl("feedback123", "lsadklasdklkdkdl", 5);
        feedback2 = new FeedbackImpl("feedback15", "lsadklasdklkdkdl", 2);
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
    public void sortByRating_should_showSortedList_when_called() {
        //Arange
        List<String> testList = new ArrayList<>();
        functionalsRepository.addWorkItem(feedback1.getId(), feedback1);
        functionalsRepository.addWorkItem(feedback2.getId(), feedback2);
        feedback1.setStatus(Status.DONE);
        feedback2.setStatus(Status.NEW);
        StringBuilder str = new StringBuilder();
        str.append(String.format("Feedback with ID:%d\n", feedback2.getId()));
        str.append("Title: feedback15\n");
        str.append("Description: lsadklasdklkdkdl\n");
        str.append("Status: New\n");
        str.append("Rating: 2\n");
        str.append("\n");
        str.append(String.format("Feedback with ID:%d\n", feedback1.getId()));
        str.append("Title: feedback123\n");
        str.append("Description: lsadklasdklkdkdl\n");
        str.append("Status: Done\n");
        str.append("Rating: 5");
        //Act and Assert
        Assert.assertEquals(str.toString(), testCommand.execute(testList));
    }
}
