package commands.actions;
import commands.actions.person.NameJoiner;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.providers.ConsoleReader;
import functionals.contracts.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static commands.actions.CommandsConstants.*;

public class ValidationCommands {
    private static Reader reader = new ConsoleReader();

    public static void validateInput(List<String> parameters, int expectedNumber) {
        if (parameters.size() != expectedNumber) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
    }

    public static void checkIfNamesMatch(String name1, String name2) {
        if (!name1.equals(name2)) {
            throw new IllegalArgumentException(WORKITEM_ID_DOES_NOT_MATCH_NAME_MSG);
        }
    }

    //generic method that checks if an Item of any type exists in the repository and throws an exception if it doesn't.
    public static <K, V, Т> void checkIfItemExists(Map<K, V> items, Т id) {
        if (!items.containsKey(id)) {
            throw new IllegalArgumentException(String.format(ITEM_DOES_NOT_EXIST_ERROR_MSG, id));
        }
    }

    /*generic method that checks if an Item contains another (for example it can check if a Board contains this
     exact WorkItem or if a Team contains this board)*/
    public static <T, G> void checkIfItemContainsAnother(List<T> listOfItems, G item,
                                                         String itemName, String secondItemName) {
        if (!listOfItems.contains(item)) {
            throw new IllegalArgumentException(String.format(ITEM_DOES_NOT_EXIST_IN_ANOTHER_MSG, itemName,
                    secondItemName));
        }
    }

    public static boolean checkIfWorkExists(int workToBeUnassigned, Person person) {
        return workToBeUnassigned > person.getAssignedWork().size();
    }

    public static String checkIfPersonExists(String personName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getPersons().containsKey(personName)) {
            System.out.printf(PERSON_DOES_NOT_EXIST_MSG, personName);
            String[] name = reader.readLine().split(" ");
            personName = NameJoiner.joinerArr(name);
        }
        return personName;
    }

    public static String checkIfMemberExists(String memberName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getPersons().containsKey(memberName)) {
            System.out.printf(MEMBER_DOES_NOT_EXIST_MSG, memberName);
            String[] name = reader.readLine().split(" ");
            memberName = NameJoiner.joinerArr(name);
        }
        return memberName;
    }

    public static String checkNameOfNewPerson(String personName, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getPersons().containsKey(personName) || personName.isEmpty()) {
            System.out.printf(PERSON_EXISTS_ERROR_MSG, personName);
            String[] name = reader.readLine().split(" ");
            personName = NameJoiner.joinerArr(name);
        }
        return personName;
    }

    public static String checkNameOfNewTeam(String name, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getTeams().containsKey(name) || name.isEmpty()) {
            System.out.printf(TEAM_EXISTS_ERROR_MSG, name);
            name = reader.readLine();
        }
        return name;
    }

    public static String checkIfMemberOfTeam(String memberName, String teamToRemoveMemberFrom, ArrayList<String> str) {
        while (!str.contains(teamToRemoveMemberFrom)) {
            System.out.printf(NOT_A_MEMBER_OF_THIS_TEAM, memberName);
            teamToRemoveMemberFrom = reader.readLine();
        }
        return teamToRemoveMemberFrom;
    }

    public static String checkIfTeamExists(String teamName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getTeams().containsKey(teamName)) {
            System.out.printf(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamName);
            teamName = reader.readLine();
        }
        return teamName;
    }

    public static String checkIfBoardExists(String boardName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getBoards().containsKey(boardName)) {
            System.out.printf(BOARD_DOES_NOT_EXIST_MSG, boardName);
            boardName = reader.readLine();
        }
        return boardName;
    }

    public static String checkBugStoryFeedback (String filterType) {
        while (!filterType.equalsIgnoreCase("bug") && !filterType.equalsIgnoreCase("story")
        && !filterType.equalsIgnoreCase("feedback")) {
            System.out.println(ENTER_BUG_STORY_FEEDBACK);
            filterType = reader.readLine();
        }
        return filterType;
    }
}
