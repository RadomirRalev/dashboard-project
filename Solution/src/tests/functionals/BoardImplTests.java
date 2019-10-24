package tests.functionals;

import core.FunctionalsRepositoryImpl;
import enums.Severity;
import enums.Size;
import functionals.models.BoardImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static functionals.models.BoardImpl.getBoardsActivity;

public class BoardImplTests {
    private BoardImpl testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;
    private Story story;
    private Bug bug;
    private List<String> steps;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new BoardImpl("bord1");
        story = new StoryImpl("name12345123131", "asjdjkasdjasijdiadijiaj", Size.MEDIUM);
        bug = new BugImpl("name12345123131", "asjdjkasdjasijdiadijiaj", Severity.CRITICAL, steps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_should_throwException_when_passedShorterName() {
        // Arrange
        String name = "Name";

        // Act & Assert
        testCommand = new BoardImpl(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_should_throwException_when_passedLongerName() {
        // Arrange
        String name = "NameThatIsLongerThanTenSymbols";

        // Act & Assert
        testCommand = new BoardImpl(name);
    }

    @Test
    public void check_If_BoardIsCreatedWhenInputIsValid() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");

        //Act
        functionalsRepository.addBoard("bordq", board);

        //Assert
        Assert.assertEquals(1, functionalsRepository.getBoards().size());
    }

    @Test
    public void check_If_StoryIsAddedToBoard() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");

        //Act
        board.addWorkItems(story);

        //Assert
        Assert.assertEquals(1, board.listWorkItems().size());
    }

    @Test
    public void check_If_BugIsAddedToBoard() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");
        Bug bug = new BugImpl("name12345123131"
                , "kasdjkasdkjaskjdkj"
                , Severity.MAJOR
                , steps);

        //Act
        board.addWorkItems(bug);

        //Assert
        Assert.assertEquals(1, board.listWorkItems().size());
    }

    @Test
    public void check_If_FeedbackIsAddedToBoard() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);


        //Act
        board.addWorkItems(feedback);

        //Assert
        Assert.assertEquals(1, board.listWorkItems().size());
    }

    @Test
    public void check_If_WorkItemsAreRemoved() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);
        board.addWorkItems(feedback);

        //Act
        board.removeWorkItems(feedback);

        //Assert
        Assert.assertEquals(0, board.listWorkItems().size());
    }

    @Test
    public void check_If_getNameReturnsName() {
        // Arrange and Act
        BoardImpl board = new BoardImpl("bord1");
        String name = "bord1";

        //Assert
        Assert.assertEquals(name, board.getName());
    }

    @Test
    public void check_If_getBoardsActivityReturnsActivityList() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);

        Map<String, ArrayList<String>> boardsActivity = new HashMap<>();

        //Act
        board.addWorkItems(feedback);
        //board.addWorkItems(feedback);

        //Assert
        Assert.assertEquals(boardsActivity, getBoardsActivity());
    }

    @Test
    public void check_If_toStringPrintIsValid() {
        // Arrange
        BoardImpl board = new BoardImpl("bord1");

        //Assert
        String str = "Board name: bord1\n" +
                "Activity history: {}\n" +
                "Work items:\n";
        Assert.assertEquals(str, board.toString());
    }

}
