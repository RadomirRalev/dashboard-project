package commands.actions.person;

import commands.actions.ConsoleInteraction;

public class PersonName extends ConsoleInteraction {
    private String teamName;
    private String personName;

    PersonName() {
    }

    String getTeamName() {
        return teamName;
    }

    void setTeamName() {
        this.teamName = asksWhich("team");
    }

    String getPersonName() {
        return personName;
    }

    void setPersonName() {
        this.personName = asksWhat();
    }
}
