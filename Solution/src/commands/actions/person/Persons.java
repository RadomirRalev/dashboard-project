package commands.actions.person;

import commands.actions.ValidationCommands;
import commands.contracts.Command;

import java.util.List;

abstract class Persons implements Command {
    private String personName;

    String getPersonName() {
        return personName;
    }

    void setPersonName() {
        this.personName = ValidationCommands.asksWhatName();
    }

    @Override
    public abstract String execute(List<String> parameters) throws Exception;
}
