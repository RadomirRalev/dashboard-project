package core.factories;

import core.contracts.FunctionalsFactory;
import enums.Severity;
import enums.Size;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.BoardImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import workitems.contracts.Bug;
import workitems.contracts.Story;
import workitems.models.BugImpl;
import workitems.models.StoryImpl;

import java.util.List;

public class FunctionalsFactoryImpl implements FunctionalsFactory {

    @Override
    public Person createPerson(String name) {
        return new PersonImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        return new TeamsImpl(name);
    }

    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public Bug createBug(String title, String description, String severity, List<String> steps) {
        return new BugImpl(title, description, getSeverity(severity), steps);
    }

    public Story createStory(String title, String description, String size){
        return new StoryImpl(title, description, getSize(size));
    }

    private Severity getSeverity(String severity) {
        return Severity.valueOf(severity.toUpperCase());
    }
    private Size getSize(String size) {
        return Size.valueOf(size.toUpperCase());
    }
}

