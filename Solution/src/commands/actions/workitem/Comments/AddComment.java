package commands.actions.workitem.Comments;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import workitems.contracts.WorkItems;

import static commands.actions.CommandsConstants.*;

public class AddComment extends CommentBase implements Command {
    private String comment;

    public AddComment(FunctionalsRepository functionalsRepository) {
        super(functionalsRepository);
    }

    protected String commentCommand(String workitemName, int id, WorkItems workitem) {
        workitem.addComment(comment);
        workitem.addHistory(COMMENT_ADDED_HISTORY_CHANGE);

        return String.format(COMMENT_SUCCESSFULLY_ADDED_MSG, workitemName);
    }

    @Override
    protected void parseParameters() {
        super.parseParameters();
        comment = asksWhatWillItBe("comment");
    }
}
