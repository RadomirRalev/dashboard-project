package tests.functionals;

import functionals.models.PersonImpl;
import org.junit.Test;

public class PersonImplTests {
    private PersonImpl testCommand;

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
}
