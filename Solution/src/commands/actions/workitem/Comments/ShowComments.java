package commands.actions.workitem.Comments;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.WorkItems;

public class ShowComments extends CommentBase implements Command {

    public ShowComments(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    @Override
    protected String commentCommand(String workitemName, int id, WorkItems workitem) {
        return workitem.showComments();
    }

}
