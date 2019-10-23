package tests.commands;

import commands.actions.ValidationCommands;
import commands.actions.person.CreateNewPerson;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import enums.Size;
import functionals.contracts.Board;
import functionals.models.BoardImpl;
import functionals.models.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;
import workitems.contracts.Feedback;
import workitems.contracts.Story;
import workitems.models.BugImpl;
import workitems.models.FeedbackImpl;
import workitems.models.StoryImpl;

import java.util.*;

public class ValidationCommandsTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        reader = new ConsoleReader();

    }

    @Test(expected = IllegalArgumentException.class)
    public void validateInput_should_throwException_when_passedIllegalArgumentsNumber() {
        // Arrange
        List<String> testList = new ArrayList<>();
        int expectedNumber = 2;

        // Act & Assert
        ValidationCommands.validateInput(testList, expectedNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfNamesMatch_should_throwException_when_passedIllegalArgumentsNumber() {
        // Arrange
        String name1 = "String1";
        String name2 = "String2";

        // Act & Assert
        ValidationCommands.checkIfNamesMatch(name1, name2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfItemExists_should_throwException_when_ItemDoesNotExist() {
        // Arrange
        Map<String, String> testMap = new HashMap<>();
        String id = "1";
        testMap.put("2", "test");
        testMap.put("3", "test");

        // Act & Assert
        ValidationCommands.checkIfItemExists(testMap, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfItemExists_should_throwException_when_passedIllegalArgumentsNumber() {
        // Arrange
        Map<String, String> testMap = new HashMap<>();
        String id = "1";
        testMap.put("2", "test");
        testMap.put("3", "test");

        // Act & Assert
        ValidationCommands.checkIfItemExists(testMap, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isFilterTypeValid_should_throwException_when_StringIsNotOfWorkItemType() {
        // Arrange
        String str = "Bugche";
        // Act & Assert
        ValidationCommands.isFilterTypeValid(str);
    }

    @Test
    public void isFilterTypeValid_should_notDoAnything_when_StringIsOfWorkItemType() {
        // Arrange
        String str = "Feedback";
        // Act & Assert
        ValidationCommands.isFilterTypeValid(str);
    }

    @Test
    public void checkIfEnumValueIsValid_should_returnEnum_when_EnumValueIsValid() {
        //Arrange
        EnumSet<Size> sizes = EnumSet.allOf(Size.class);
        String value = "Large";
        String enumType = "Size";
        String enumFilter = "Filter";
        //Act and Assert
        Assert.assertEquals(value, ValidationCommands.checkIfEnumValueIsValid(
                value, sizes, enumType, enumFilter));
    }

    @Test(expected = IllegalArgumentException.class)
    public void castStory_should_throwException_when_WorkItemIsNotStory() {
        // Arrange
        Feedback feedback = new FeedbackImpl("feedback123", "salkdalkdakl", 4);
        // Act & Assert
        ValidationCommands.castStory(feedback);
    }

    @Test(expected = IllegalArgumentException.class)
    public void castBug_should_throwException_when_WorkItemIsNotBug() {
        // Arrange
        Feedback feedback = new FeedbackImpl("feedback123", "salkdalkdakl", 4);
        // Act & Assert
        ValidationCommands.castBug(feedback);
    }

    @Test(expected = IllegalArgumentException.class)
    public void castBugAndStory_should_throwException_when_WorkItemIsNotBugAndStory() {
        // Arrange
        Feedback feedback = new FeedbackImpl("feedback123", "salkdalkdakl", 4);
        // Act & Assert
        ValidationCommands.castBugAndStory(feedback);
    }

    @Test(expected = IllegalArgumentException.class)
    public void castFeedback_should_throwException_when_WorkItemIsNotFeedback() {
        // Arrange
        Story story = new StoryImpl("story12345", "salkdalkdakl", Size.LARGE);
        // Act & Assert
        ValidationCommands.castFeedback(story);
    }

    @Test
    public void checkIfTitleLengthIsValid_should_returnTitle_when_Valid() {
        // Arrange
        String title = "title12345";
        // Act & Assert
        Assert.assertEquals(title, ValidationCommands.checkIfTitleLengthIsValid(title));
    }

    @Test
    public void checkIfDescriptionLengthIsValid_should_returnDescription_when_Valid() {
        // Arrange
        String description = "description12345";
        // Act & Assert
        Assert.assertEquals(description, ValidationCommands.checkIfDescriptionLengthIsValid(description));
    }

    @Test
    public void checkIfWorkItemExists_should_returnID_when_Valid() {
        // Arrange
        int id = 1;
        Story story = new StoryImpl("story12345", "salkdalkdakl", Size.LARGE);
        functionalsRepository.addWorkItem(id, story);
        // Act & Assert
        Assert.assertEquals(id, ValidationCommands.checkIfWorkItemExists(id, functionalsRepository));
    }

    @Test
    public void checkBugStoryFeedback_should_returnString_when_Valid() {
        // Arrange
        String str = "Feedback";
        // Act & Assert
        Assert.assertEquals(str, ValidationCommands.checkBugStoryFeedback(str));
    }

    @Test
    public void checkIfBoardExists_should_returnString_when_Valid() {
        // Arrange
        String boardName = "bordche";
        Board board = new BoardImpl("bordche");
        functionalsRepository.addBoard("bordche", board);
        // Act & Assert
        Assert.assertEquals(boardName
                , ValidationCommands.checkIfBoardExists(boardName, functionalsRepository));
    }

    @Test
    public void checkIfRatingIsValid_should_returnString_when_Valid() {
        // Arrange
        String str = "4";
        // Act & Assert
        Assert.assertEquals(str, ValidationCommands.checkIfRatingIsValid(str));
    }

    @Test
    public void checkIfStringCanBeParsed_should_returnString_when_Valid() {
        // Arrange
        String str = "50";
        int newInt = 50;
        // Act & Assert
        Assert.assertEquals(newInt, ValidationCommands.checkIfStringCanBeParsed(str));
    }

}
