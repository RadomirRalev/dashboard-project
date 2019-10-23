package commands.actions;

import commands.actions.person.NameJoiner;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.providers.ConsoleReader;
import functionals.contracts.Person;
import functionals.models.MemberImpl;
import functionals.models.ValidationHelper;

import java.util.*;
import java.util.stream.Collectors;

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
            personName = reader.readLine();
            if (personName.equalsIgnoreCase("cancel")) {
                return personName;
            }
            personName = trimInputAndCheckIfStringIsEmpty(personName);
            String[] name = personName.split(" ");
            personName = NameJoiner.joinerArr(name);
        }
        return personName;
    }

    public static String checkIfMemberExists(String memberName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getPersons().containsKey(memberName)) {
            System.out.printf(MEMBER_DOES_NOT_EXIST_MSG, memberName);
            memberName = reader.readLine();
            memberName = trimInputAndCheckIfStringIsEmpty(memberName);
            String[] name = memberName.split(" ");
            memberName = NameJoiner.joinerArr(name);
        }
        return memberName;
    }

    public static String checkNameOfNewPerson(String personName, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getPersons().containsKey(personName)) {
            System.out.printf(PERSON_EXISTS_ERROR_MSG, personName);
            personName = reader.readLine();
            personName = trimInputAndCheckIfStringIsEmpty(personName);
            String[] name = personName.split(" ");
            personName = NameJoiner.joinerArr(name);
            personName = ValidationCommands.checkNameLengthOfNewPerson(personName, functionalsRepository);
        }
        return personName;
    }

    public static String checkNameLengthOfNewPerson(String personName, FunctionalsRepository functionalsRepository) {
        while (personName.length() < 5 || personName.length() > 15) {
            System.out.println(PERSON_NAME_LENGTH);
            personName = reader.readLine();
            personName = trimInputAndCheckIfStringIsEmpty(personName);
            String[] name = personName.split(" ");
            personName = NameJoiner.joinerArr(name);
        }
        return personName;
    }

    public static String checkNameOfNewTeam(String name, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getTeams().containsKey(name)) {
            System.out.printf(TEAM_EXISTS_ERROR_MSG, name);
            name = reader.readLine();
            name = trimInputAndCheckIfStringIsEmpty(name);
        }
        return name;
    }

    public static String checkIfMemberOfTeam(String memberName, String teamToRemoveMemberFrom, ArrayList<String> str) {
        while (!str.contains(teamToRemoveMemberFrom)) {
            System.out.printf(NOT_A_MEMBER_OF_THIS_TEAM, memberName);
            teamToRemoveMemberFrom = reader.readLine();
            teamToRemoveMemberFrom = trimInputAndCheckIfStringIsEmpty(teamToRemoveMemberFrom);
        }
        return teamToRemoveMemberFrom;
    }

    public static boolean checkIfTeamMemberExists(String personName, List<MemberImpl> str) {
        List<String> membersOfTeam = new ArrayList<>();
        str.forEach(member -> membersOfTeam.add(member.getName()));
        return membersOfTeam.contains(personName);
    }

    public static String checkIfTeamExists(String teamName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getTeams().containsKey(teamName)) {
            if (teamName.equalsIgnoreCase("cancel")) {
                return teamName;
            }
            System.out.printf(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamName);
            teamName = reader.readLine();
            teamName = trimInputAndCheckIfStringIsEmpty(teamName);
        }
        return teamName;
    }

    public static String checkIfBoardExists(String boardName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getBoards().containsKey(boardName)) {
            System.out.printf(BOARD_DOES_NOT_EXIST_MSG, boardName);
            boardName = reader.readLine();
            boardName = trimInputAndCheckIfStringIsEmpty(boardName);
        }
        return boardName;
    }

    public static int checkIfWorkItemExists(int id, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getWorkItems().containsKey(id)) {
            System.out.printf(WORKITEM_DOES_NOT_EXIST_MSG, id);
            id = checkIfStringCanBeParsed(reader.readLine());
        }
        return id;
    }

    public static String checkBugStoryFeedback(String filterType) {
        while (!filterType.equalsIgnoreCase("bug") && !filterType.equalsIgnoreCase("story")
                && !filterType.equalsIgnoreCase("feedback")) {
            System.out.println(ENTER_BUG_STORY_FEEDBACK);
            filterType = reader.readLine();
        }
        return filterType;
    }

    public static String trimInputAndCheckIfStringIsEmpty(String input) {
        while (input.trim().isEmpty()) {
            System.out.println("Input cannot be empty! Please write another name: ");
            input = reader.readLine();
        }
        return input;
    }

    public static void isFilterTypeValid(String filterType) {
        if (!filterType.equalsIgnoreCase(BUG)
                && !filterType.equalsIgnoreCase(STORY)
                && !filterType.equalsIgnoreCase(FEEDBACK)) {
            throw new IllegalArgumentException(String.format(INVALID_COMMAND, filterType));
        }
    }

    public static <T extends Enum<T>> String checkIfEnumValueIsValid(String enumValue, EnumSet<T> enumSet
            , String enumType, String enumFilters) {
        while (true) {
            for (T t : enumSet) {
                if (t.toString().equalsIgnoreCase(enumValue)) {
                    return enumValue;
                }
            }
            System.out.println(String.format("Not a valid %s. Please choose from %s", enumType, enumFilters));
            enumValue = reader.readLine();
        }
    }

    public static String checkIfRatingIsValid(String rating) {
        while (true) {
            try {
                int ratingInt = Integer.parseInt(rating);
                ValidationHelper.checkNumberInBounds(ratingInt, RATING_MIN_VALUE, RATING_MAX_VALUE);
                return rating;
            } catch (Exception ex) {
                System.out.println(String.format("This value is not valid. Please note that the rating" +
                        " can only be an integer between %d and %d", RATING_MIN_VALUE, RATING_MAX_VALUE));
            }
            rating = reader.readLine();
        }
    }

    public static int checkIfStringCanBeParsed(String idString) {
        int id;
        while (true) {
            try {
                id = Integer.parseInt(idString);
                return id;
            } catch (Exception ex) {
                System.out.println("ID must be an integer. Please enter a valid one");
            }
            idString = reader.readLine();
        }
    }
}