package tests.commands.workitems;

import commands.actions.person.CreateNewPerson;
import commands.actions.workitem.Create.CreateStory;
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
import workitems.contracts.Story;
import workitems.models.StoryImpl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CreateStoryTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateStory(functionalsFactory, functionalsRepository);
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
    public void setTitle_should_throwException_when_passedStringLengthLessThan10() {
        // Arrange
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            name.append(" ");
        }
        // Act & Assert
        Story story = new StoryImpl(name.toString(), "descriptisaodkjas", Size.MEDIUM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitle_should_throwException_when_passedStringLengthMoreThan50() {
        // Arrange
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 51; i++) {
            name.append(" ");
        }
        // Act & Assert
        Story story = new StoryImpl(name.toString(), "descriptisaodkjas", Size.MEDIUM);

    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescription_should_throwException_when_passedStringLengthLessThan10() {
        // Arrange
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            name.append(" ");
        }
        // Act & Assert
        Story story = new StoryImpl("name12345123131", name.toString(), Size.MEDIUM);

    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescription_should_throwException_when_passedStringLengthMoreThan500() {
        // Arrange
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 501; i++) {
            name.append(" ");
        }
        // Act & Assert
        Story story = new StoryImpl("name12345123131", name.toString(), Size.MEDIUM);

    }

    @Test(expected = IllegalArgumentException.class)
    public void setStatus_should_throwException_when_passedInvalidStatus() {
        // Arrange
        Story story = new StoryImpl("name12345123131", "kasdjkasdkjaskjdkj", Size.MEDIUM);
        // Act & Assert
        story.setStatus(Status.FIXED);

    }

    @Test
    public void check_If_GetSizeReturnsValidSize() {
        // Arrange
        Story story = new StoryImpl("StoryTitle123456", "Description1231sajkdjk", Size.LARGE);

        //Assert and Act
        Assert.assertEquals(Size.LARGE, story.getSize());
    }

    @Test
    public void check_If_SetSizeChangesSize() {
        // Arrange
        Story story = new StoryImpl("StoryTitle123456", "Description1231sajkdjk", Size.LARGE);

        //Act
        story.setSize(Size.SMALL);

        //Assert and Act
        Assert.assertEquals(Size.SMALL, story.getSize());
    }

    @Test
    public void check_If_GetSizeListReturnsValidListOfSizes() {
        //Arrange and Act
        EnumSet<Size> enumSet = EnumSet.allOf(Size.class);
        //Act and Assert
        Assert.assertEquals(enumSet, StoryImpl.getSizeList());
    }

    @Test
    public void check_If_StoryCreatedWhenInputIsValid() {
        // Arrange
        Story story = new StoryImpl("StoryTitle123456", "Description1231sajkdjk", Size.LARGE);

        //Act
        functionalsRepository.addWorkItem(story.getId(), story);

        //Assert
        Assert.assertEquals(1, functionalsRepository.getWorkItems().size());
    }
}
