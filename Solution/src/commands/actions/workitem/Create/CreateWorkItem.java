package commands.actions.workitem.Create;

import commands.actions.ConsoleInteraction;
import commands.actions.ValidationCommands;
import commands.contracts.Command;
import core.contracts.FunctionalsFactory;
import core.contracts.FunctionalsRepository;

import java.util.List;

public abstract class CreateWorkItem extends ConsoleInteraction implements Command {
    private final FunctionalsRepository functionalsRepository;
    private final FunctionalsFactory functionalsFactory;

    private String boardName;
    private String title;
    private String description;
    private String changeableParameter;

    public CreateWorkItem(FunctionalsFactory functionalsFactory, FunctionalsRepository functionalsRepository) {
        this.functionalsFactory = functionalsFactory;
        this.functionalsRepository = functionalsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ConsoleInteraction.validateInput(parameters.size());

        parseParameters();

        return createCommand(title, description, boardName, changeableParameter, functionalsFactory, functionalsRepository);
    }

    public String getChangeableParameter() {
        return changeableParameter;
    }

    protected abstract String createCommand(String title
            , String description
            , String boardName
            , String changeableParameter
            , FunctionalsFactory functionalsFactory
            , FunctionalsRepository functionalsRepository);

    protected void parseParameters() {
        boardName = ConsoleInteraction.asksWhich("board");
        boardName = ValidationCommands.checkIfBoardExists(boardName, functionalsRepository);

        title = asksWhatWillItBe("title");
        description = asksWhatWillItBe("description");
    }

    protected void setChangeableParameter(String changeableParameter) {
        this.changeableParameter = changeableParameter;
    }
}
