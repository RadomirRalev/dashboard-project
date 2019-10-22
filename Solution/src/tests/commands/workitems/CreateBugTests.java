package tests.commands.workitems;

import commands.actions.workitem.Create.CreateBug;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import enums.Priority;
import enums.Severity;
import enums.Status;
import functionals.contracts.Person;
import functionals.models.PersonImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workitems.contracts.Bug;
import workitems.models.BugImpl;

import java.util.ArrayList;
import java.util.List;

public class CreateBugTests {
    private Command testCommand;
    private FunctionalsFactoryImpl functionalsFactory;
    private FunctionalsRepositoryImpl functionalsRepository;
    private static Reader reader;
    private List<String> steps;
    private Person person;

    @Before
    public void before() {
        functionalsFactory = new FunctionalsFactoryImpl();
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new CreateBug(functionalsFactory, functionalsRepository);
        reader = new ConsoleReader();
        steps = new ArrayList<>();
        person = new PersonImpl("pesho");
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
        steps.add("Steps 1.");
        steps.add("Second step added.");
        Bug bug = new BugImpl("name12345123131"
                , "kasdjkasdkjaskjdkj"
                , Severity.MAJOR
                , steps);
        // Act & Assert
        bug.setStatus(Status.NEW);
    }

    @Test
    public void setPriority_should_changePriority_when_passedValidPriority() {
        //Arrange
        steps.add("Steps 1.");
        steps.add("Second step added.");
        Bug bug = new BugImpl("name12345123131"
                , "kasdjkasdkjaskjdkj"
                , Severity.MAJOR
                , steps);
        // Act
        bug.setPriority(Priority.MEDIUM);

        // Asses
        Assert.assertEquals(Priority.MEDIUM, bug.getPriority());
    }

    @Test
    public void setSeverity_should_changeSeverity_when_passedValidSeverity() {
        //Arrange
        steps.add("Steps 1.");
        steps.add("Second step added.");
        Bug bug = new BugImpl("name12345123131"
                , "kasdjkasdkjaskjdkj"
                , Severity.MAJOR
                , steps);
        // Act
        bug.setSeverity(Severity.MAJOR);

        // Asses
        Assert.assertEquals(Severity.MAJOR, bug.getSeverity());
    }

    @Test
    public void setAsignee_should_changeAsignee_when_passedValidAsignee() {
        //Arrange
        steps.add("Steps 1.");
        steps.add("Second step added.");
        Bug bug = new BugImpl("name12345123131"
                , "kasdjkasdkjaskjdkj"
                , Severity.MAJOR
                , steps);
        // Act
        bug.setAsignee(person);

        // Asses
        Assert.assertEquals(person, bug.getAsignee());
    }

    @Test
    public void check_If_BugCreatedWhenInputIsValid() {
        // Arrange
        steps.add("Steps 1.");
        steps.add("Second step added.");
        Bug bug = new BugImpl("StoryTitle123456"
                , "Description1231sajkdjk"
                , Severity.MINOR
                , steps);

        //Act
        functionalsRepository.addWorkItem(bug.getId(), bug);

        //Assert
        Assert.assertEquals(1, functionalsRepository.getWorkItems().size());
    }
}
