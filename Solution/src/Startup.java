import core.EngineImpl;
import enums.Priority;
import enums.Severity;
import enums.Size;
import enums.Status;
import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.models.BoardImpl;
import functionals.models.PersonImpl;
import workitems.contracts.WorkItems;
import workitems.models.*;

import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void main(String[] args) {
        EngineImpl engine = new EngineImpl();
        engine.start();

    }
}
