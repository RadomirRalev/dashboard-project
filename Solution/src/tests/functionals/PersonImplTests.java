package tests.functionals;

import core.FunctionalsRepositoryImpl;
import enums.Severity;
import enums.Size;
import functionals.contracts.Person;
import functionals.models.BoardImpl;
import functionals.models.PersonImpl;
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
import static functionals.models.PersonImpl.getMembersActivity;

public class PersonImplTests {
    private PersonImpl testCommand;
    private Story story;
    private Bug bug;
    private List<String> steps;


    @Before
    public void before() {
        story = new StoryImpl("name12345123131", "asjdjkasdjasijdiadijiaj", Size.MEDIUM);
        bug = new BugImpl(  "name12345123131", "asjdjkasdjasijdiadijiaj", Severity.CRITICAL, steps);
    }


    @Test(expected = IllegalArgumentException.class)
    public void setName_should_throwException_when_passedShorterName() {
        // Arrange
        String name = "Name";

        // Act & Assert
        testCommand = new PersonImpl(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_should_throwException_when_passedLongerName() {
        // Arrange
        String name = "NameThatIsLongerThanFifteenSymbols";

        // Act & Assert
        testCommand = new PersonImpl(name);
    }

    @Test
    public void check_If_getBoardsActivityReturnsActivityList() {
        // Arrange
        PersonImpl person = new PersonImpl("Totototo");
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);
        person.addWorkItems(feedback);
        Map<String, ArrayList<String>> membersActivity = new HashMap<>();

        //Act
        person.addWorkItems(feedback);

        //Assert
        Assert.assertEquals(membersActivity, getBoardsActivity());
    }

    @Test
    public void check_If_getNameReturnsName() {
        // Arrange and Act
        PersonImpl person = new PersonImpl("Totototo");
        String name = "Totototo";

        //Assert
        Assert.assertEquals(name, person.getName());
    }

    @Test
    public void check_If_getMembersActivityReturnsActivityList() {
        // Arrange
        PersonImpl person = new PersonImpl("Totototo");
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);
        person.addWorkItems(feedback);
        Map<String, ArrayList<String>> membersActivity = new HashMap<>();

        //Act
        person.addWorkItems(feedback);

        //Assert
        Assert.assertEquals(membersActivity, getMembersActivity());
    }

    @Test
    public void check_If_StoryIsAddedToPerson() {
        // Arrange
        PersonImpl person = new PersonImpl("Totototo");
        story = new StoryImpl("name12345123131", "asjdjkasdjasijdiadijiaj", Size.MEDIUM);

        //Act
        person.addWorkItems(story);

        //Assert
        Assert.assertEquals(1, person.listWorkItems().size());
    }

    @Test
    public void check_If_BugIsAddedToPerson() {
        // Arrange
        PersonImpl person = new PersonImpl("Totototo");
        Bug bug = new BugImpl("name12345123131"
                , "kasdjkasdkjaskjdkj"
                , Severity.MAJOR
                , steps);

        //Act
        person.addWorkItems(bug);

        //Assert
        Assert.assertEquals(1, person.listWorkItems().size());
    }

    @Test
    public void check_If_FeedbackIsAddedToPerson() {
        // Arrange
        PersonImpl person = new PersonImpl("Totototo");
        Feedback feedback = new FeedbackImpl("name12345123131", "kasdjkasdkjaskjdkj", 4);

        //Act
        person.addWorkItems(feedback);

        //Assert
        Assert.assertEquals(1, person.listWorkItems().size());
    }

    @Test
    public void check_If_toStringPrintIsValid() {
        // Arrange
        PersonImpl person = new PersonImpl("Totototo");

        //Assert
        String str = "Name: Totototo\n" +
        "Activity history: \n" +
        "Work items:\n";
        Assert.assertEquals(str, person.toString());
    }

}
