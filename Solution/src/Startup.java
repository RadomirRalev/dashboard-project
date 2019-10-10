import core.EngineImpl;
import enums.Priority;
import enums.Severity;
import enums.Size;
import enums.Status;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.models.BoardImpl;
import functionals.models.PersonImpl;
import workitems.contracts.Bug;
import workitems.contracts.WorkItems;
import workitems.models.*;

import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void main(String[] args) {
        EngineImpl engine = new EngineImpl();
        engine.start();

        List<String> list = new ArrayList<>();
        list.add("56");
        list.add("57");
        list.add("55");
        Bug bug1 = new BugImpl("namepeshopicha", "descriptionpeshopicha", Severity.MINOR, list);
        StoryImpl story1 = new StoryImpl("namepeshopicha", "descriptionpeshopicha", Size.SMALL);
        Person pesho = new PersonImpl("Pesho");
        FeedbackImpl feedback1 = new FeedbackImpl("feedbackpeshkawo", "iwjaskahsakhk",4);

        Board board = new BoardImpl("bordche");
        board.addWorkItems((BugImpl)bug1);
        board.addWorkItems(story1);
        board.addWorkItems(feedback1);
        //bug1.setAssignee(pesho);
        story1.setAssignee(pesho);
       // bug1.setPriority(Priority.LOW);
        story1.setPriority(Priority.MEDIUM);
       // bug1.setStatus(Status.ACTIVE);
        story1.setStatus(Status.ACTIVE);

        for(Object item: board.listWorkItems()){
            System.out.println(item);
        }
//        System.out.println(board.listWorkItems());
    }
}
