package tests.commands.workitems;

import commands.actions.workitem.Create.CreateFeedback;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import enums.Size;
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
