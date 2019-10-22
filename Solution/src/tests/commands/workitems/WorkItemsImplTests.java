package tests.commands.workitems;

import commands.actions.workitem.Create.CreateStory;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import enums.Size;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Story;
import workitems.models.StoryImpl;

public class WorkItemsImplTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;
    private Story story;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateStory(functionalsFactory, functionalsRepository);
        reader = new ConsoleReader();
        story = new StoryImpl("name12345123131", "asjdjkasdjasijdiadijiaj", Size.MEDIUM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addComment_should_throwException_when_passedEmptyString() {
        // Arrange
        String emptyString = "";
        // Act & Assert
        story.addComment(emptyString);
    }

    @Test
    public void check_If_CommentIsAddedWhenInputIsValid() {
        // Arrange and Act
        story.addComment("skladkajsdk");
        story.addComment("skladk213ajsdk");

        //Assert
        Assert.assertEquals(2, story.getComments().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void check_If_ShowCommentsThrowsExceptionWhenEmpty() {
        // Arrange
        story.addComment("");


        //Act and Assert
        story.showComments();
    }

    @Test
    public void check_If_HistoryIsAddedWhenInputIsValid() {
        // Arrange
        story.addHistory("sadads");
        story.addHistory("23aslkd");


        //Act and Assert
        Assert.assertEquals(3, story.getHistory().size());
    }

    @Test
    public void check_If_ShowCommentsPrintIsValid() {
        // Arrange and Act
        story.addComment("skladkajsdk");
        story.addComment("skladk213ajsdk");
        StringBuilder str = new StringBuilder();
        str.append("skladkajsdk\n");
        str.append("skladk213ajsdk");

        //Assert
        Assert.assertEquals(str.toString(), story.showComments());
    }
}
