package commands.actions.workitem;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;

import java.util.List;

public class AddComment implements Command {
    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 3;

    private final FunctionalsRepository functionalsRepository;

    private String workitemName;
    private int id;
    private String boardName;
    private String comment;

    @Override
    public String execute(List<String> parameters) throws Exception {
        return null;
    }

    private parseParameters(List<String> parameters){
        
    }
}
