package core.contracts;

import commands.contracts.Command;
import core.FunctionalsRepositoryImpl;

public interface CommandFactory {
    Command createCommand(String commandTypeAsString, FunctionalsFactory functionalsFactory,
                          FunctionalsRepositoryImpl functionalsRepository);
}
