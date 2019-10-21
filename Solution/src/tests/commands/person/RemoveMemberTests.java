package tests.commands.person;

import commands.actions.person.AssignWorkToPerson;
import commands.actions.person.RemoveMember;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class RemoveMemberTests {
    private Command testCommand;
    private FunctionalsRepositoryImpl functionalsRepository;

    @Before
    public void before() {
        functionalsRepository = new FunctionalsRepositoryImpl();
        testCommand = new RemoveMember(functionalsRepository);
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
    public void check_If_PersonIsAddedToTeamWhenInputIsValid() {
        // Arrange
        MemberImpl member = new MemberImpl("Name", functionalsRepository);
        functionalsRepository.addMember("Name", member);

        //Act
        functionalsRepository.removeMember("Name");

        //Assert
        Assert.assertEquals(0, functionalsRepository.getMembers().size());

    }
}
