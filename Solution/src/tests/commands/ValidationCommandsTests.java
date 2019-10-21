package tests.commands;

import commands.actions.ValidationCommands;
import commands.actions.person.CreateNewPerson;
import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;
import core.contracts.Reader;
import core.factories.FunctionalsFactoryImpl;
import core.providers.ConsoleReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
