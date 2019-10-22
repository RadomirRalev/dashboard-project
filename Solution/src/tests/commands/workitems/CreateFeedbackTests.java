package tests.commands.workitems;

import commands.actions.workitem.Create.CreateFeedback;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import enums.Size;
import enums.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Feedback;
import workitems.models.FeedbackImpl;

import java.util.ArrayList;
import java.util.List;

public class CreateFeedbackTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateFeedback(functionalsFactory, functionalsRepository);
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

    @Test(expected = IllegalArgumentException.class)
    public void setStatus_should_throwException_when_passedInvalidStatus() {
        // Arrange
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);
        // Act & Assert
        feedback.setStatus(Status.FIXED);

    }

    @Test
    public void check_If_SetRatingChangesRating() {
        // Arrange
        Feedback feedback = new FeedbackImpl("StoryTitle123456", "Description1231sajkdjk", 4);

        //Act
        feedback.setRating(2);

        //Assert and Act
        Assert.assertEquals(2, feedback.getRating());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRating_should_throwException_when_passedInvalidRating() {
        // Arrange
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);

        //Act and Assert
        feedback.setRating(6);
    }

    @Test
    public void check_If_FeedbackCreatedWhenInputIsValid() {
        // Arrange
        Feedback feedback = new FeedbackImpl("StoryTitle123456", "Description1231sajkdjk", 4);

        //Act
        functionalsRepository.addWorkItem(feedback.getId(), feedback);

        //Assert
        Assert.assertEquals(1, functionalsRepository.getWorkItems().size());
    }
}
