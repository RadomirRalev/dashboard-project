package commands.actions.workitem.ListWorkItems;

import commands.contracts.Command;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;

import java.util.List;

public abstract class ListWorkItems implements Command {
    private FunctionalsRepository functionalsRepository;
    private Reader reader;
    private Writer writer;

    public ListWorkItems(FunctionalsRepository functionalsRepository) {
        this.functionalsRepository = functionalsRepository;
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
    }

    @Override
    public String execute(List<String> parameters) throws IllegalArgumentException {

        StringBuilder stringBuilder = new StringBuilder();

        return listCommand(stringBuilder);
    }

    protected FunctionalsRepository getFunctionalsRepository() {
        return functionalsRepository;
    }

    protected Reader getReader() {
        return reader;
    }

    protected Writer getWriter() {
        return writer;
    }

    protected abstract String listCommand(StringBuilder stringBuilder);
}
