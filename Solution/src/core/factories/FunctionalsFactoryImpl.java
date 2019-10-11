package core.factories;

import core.contracts.FunctionalsFactory;
import enums.Severity;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import functionals.models.BoardImpl;
import functionals.models.PersonImpl;
import functionals.models.TeamsImpl;
import workitems.contracts.Bug;
import workitems.models.BugImpl;

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

    private Severity getSeverity(String severity) {
        return Severity.valueOf(severity.toUpperCase());
    }
}

