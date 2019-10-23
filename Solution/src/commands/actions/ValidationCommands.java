package commands.actions;

import commands.actions.person.NameJoiner;
import core.contracts.FunctionalsRepository;
import core.contracts.Reader;
import core.contracts.Writer;
import core.providers.ConsoleReader;
import core.providers.ConsoleWriter;
import functionals.contracts.Person;
import functionals.models.MemberImpl;
import functionals.models.ValidationHelper;
import workitems.contracts.*;

import java.util.*;

import static commands.actions.CommandsConstants.*;

public class ValidationCommands {
    private static Writer writer;
    private static Reader reader;

    static {
        writer = new ConsoleWriter();
        reader = new ConsoleReader();
    }

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
            writer.write(String.format(PERSON_DOES_NOT_EXIST_MSG, personName));
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
            writer.write(String.format(MEMBER_DOES_NOT_EXIST_MSG, memberName));
            memberName = reader.readLine();
            memberName = trimInputAndCheckIfStringIsEmpty(memberName);
            String[] name = memberName.split(" ");
            memberName = NameJoiner.joinerArr(name);
        }
        return memberName;
    }

    public static String checkNameOfNewPerson(String personName, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getPersons().containsKey(personName)) {
            writer.write(String.format(PERSON_EXISTS_ERROR_MSG, personName));
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
            writer.writeLine(PERSON_NAME_LENGTH);
            personName = reader.readLine();
            personName = trimInputAndCheckIfStringIsEmpty(personName);
            String[] name = personName.split(" ");
            personName = NameJoiner.joinerArr(name);
        }
        return personName;
    }

    public static String checkNameOfNewTeam(String name, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getTeams().containsKey(name)) {
            writer.write(String.format(TEAM_EXISTS_ERROR_MSG, name));
            name = reader.readLine();
            name = trimInputAndCheckIfStringIsEmpty(name);
        }
        return name;
    }

    public static String checkIfMemberOfTeam(String memberName, String teamToRemoveMemberFrom, ArrayList<String> str) {
        while (!str.contains(teamToRemoveMemberFrom)) {
            writer.write(String.format(NOT_A_MEMBER_OF_THIS_TEAM, memberName));
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
            writer.write(String.format(TEAM_DOES_NOT_EXIST_ERROR_MSG, teamName));
            teamName = reader.readLine();
            teamName = trimInputAndCheckIfStringIsEmpty(teamName);
        }
        return teamName;
    }

    public static String checkIfBoardExists(String boardName, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getBoards().containsKey(boardName)
                && !boardName.equalsIgnoreCase("cancel")) {
            writer.write(String.format(BOARD_DOES_NOT_EXIST_MSG, boardName));
            boardName = reader.readLine();
            boardName = trimInputAndCheckIfStringIsEmpty(boardName);
        }
        return boardName;
    }

    public static String checkIfBoardAlreadyExistsInTeam(String boardName, String teamName, FunctionalsRepository functionalsRepository) {
        while (functionalsRepository.getTeams().get(teamName).getBoards().contains(
                functionalsRepository.getBoards().get(boardName))
                && !boardName.equalsIgnoreCase("cancel")) {
            writer.writeLine(String.format(BOARD_ALREADY_EXISTS_IN_TEAM, boardName, teamName));
            boardName = reader.readLine();
            boardName = trimInputAndCheckIfStringIsEmpty(boardName);
        }
        return boardName;
    }


    public static int checkIfWorkItemExists (int id, FunctionalsRepository functionalsRepository) {
        while (!functionalsRepository.getWorkItems().containsKey(id)
                && id != 0) {
            writer.writeLine(String.format(WORKITEM_DOES_NOT_EXIST_MSG, id));
            id = checkIfStringCanBeParsed(reader.readLine());
        }
        return id;
    }

    public static String checkBugStoryFeedback(String filterType) {
        while (!filterType.equalsIgnoreCase(BUG) && !filterType.equalsIgnoreCase(STORY)
                && !filterType.equalsIgnoreCase(FEEDBACK)) {
            writer.writeLine(ENTER_BUG_STORY_FEEDBACK);
            filterType = reader.readLine();
        }
        return filterType;
    }

        public static String checkIfTitleLengthIsValid(String title) {
        while ((title.length() < WORKITEM_TITLE_MIN_LENGTH || title.length() > WORKITEM_TITLE_MAX_LENGTH)
                && !title.equalsIgnoreCase("cancel")) {
            writer.write(String.format(TITLE_LENGTH_OUT_OF_BOUNDS, title));
            title = reader.readLine();
            title = trimInputAndCheckIfStringIsEmpty(title);
        }
        return title;
    }

    public static String checkIfDescriptionLengthIsValid(String description) {
        while ((description.length() < WORKITEM_DESCRIPTION_MIN_LENGTH || description.length() > WORKITEM_DESCRIPTION_MAX_LENGTH)
                && !description.equalsIgnoreCase("cancel")) {
            writer.write(String.format(DESCRIPTION_LENGTH_OUT_OF_BOUNDS, description));
            description = reader.readLine();
            description = trimInputAndCheckIfStringIsEmpty(description);
        }
        return description;
    }


    public static String trimInputAndCheckIfStringIsEmpty(String input) {
        while (input.trim().isEmpty()) {
            writer.writeLine(INPUT_EMPTY_ERROR_WRITE_ANOTHER_NAME);
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
            writer.writeLine(String.format(ENUM_NOT_VALID_PLEASE_CHOOSE_FROM_FILTER, enumType, enumFilters));
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
                writer.writeLine(String.format(INVALID_RATING_ERROR_MSG, RATING_MIN_VALUE, RATING_MAX_VALUE));
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
                writer.writeLine(PLEASE_ENTER_VALID_INTEGER);
            }
            idString = reader.readLine();
        }
    }

    public static StringBuilder isStringBuilderEmpty(StringBuilder stringBuilder) {
        if (!stringBuilder.toString().isEmpty()) {
            return stringBuilder;
        } else {
            stringBuilder.append(NO_WORKITEMS_MEET_CRITERIA);
            return stringBuilder;
        }
    }

    public static BugAndStory castBugAndStory(WorkItems workitem) {
        try {
            BugAndStory bugAndStory = (BugAndStory) workitem;
            return bugAndStory;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(String.format(WORKITEM_NOT_A_OR, BUG, STORY));
        }
    }

    public static Bug castBug(WorkItems workitem) {
        try {
            Bug bug = (Bug) workitem;
            return bug;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(String.format(WORKITEM_NOT_A, BUG));
        }
    }

    public static Feedback castFeedback(WorkItems workitem) {
        try {
            Feedback feedback = (Feedback) workitem;
            return feedback;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(String.format(WORKITEM_NOT_A, FEEDBACK));
        }
    }

    public static Story castStory(WorkItems workitem) {
        try {
            Story story = (Story) workitem;
            return story;
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(String.format(WORKITEM_NOT_A, STORY));
        }
    }
}