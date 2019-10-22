package tests.functionals;

import functionals.models.BoardImpl;
import org.junit.Test;

public class BoardImplTests {
    private BoardImpl testCommand;


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

}
