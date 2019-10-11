package core.contracts;

import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;
import workitems.contracts.Bug;
import workitems.contracts.Feedback;
import workitems.contracts.Story;

import java.util.List;

public interface FunctionalsFactory {
    Person createPerson(String name);
    Team createTeam(String name);
    Board createBoard(String name);
    Bug createBug(String name, String description, String severity, List<String> steps);
    Story createStory(String title, String description, String size);
    Feedback createFeedback(String title, String decription, int rating);
}
