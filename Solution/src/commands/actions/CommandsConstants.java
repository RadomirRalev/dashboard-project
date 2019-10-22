package commands.actions;

public class CommandsConstants {
    public static final int RATING_MIN_VALUE = 1;
    public static final int RATING_MAX_VALUE = 5;
    public static final String INVALID_COMMAND = "Invalid command name: %s!";
    public static final String PERSON_CREATED_MSG = "Person %s was created!";
    public static final String PERSON_DELETED_MSG = "Person %s was deleted!";
    public static final String PERSON_EXISTS_ERROR_MSG = "Name %s already exists in the database " +
            "but you can add a family name or some identifier to create this person. " +
            "Please type cancel to abort or change name: \n";
    public static final String PERSON_NAME_LENGTH = "The length of a person name should be between 5 and 15 symbols. " +
            "Please type cancel to abort or change name:";
    public static final String TEAM_EXISTS_ERROR_MSG = "Team %s already exists in the database " +
            "Please type cancel to abort or change name: \n";
    public static final String BOARD_EXISTS_ERROR_MSG = "Board %s already exists in the database.";
    public static final String MEMBER_DOES_NOT_EXIST_MSG = "Member %s does not exist in the database " +
            "but you can write a new name to correct your search. " +
            "Please type cancel to abort or change name: \n";
    public static final String PERSON_DOES_NOT_EXIST_MSG = "Person %s does not exist in the database " +
            "but you can write a new name to correct your search. " +
            "Please type cancel to abort or change name: \n";
    public static final String TEAM_DOES_NOT_EXIST_ERROR_MSG = "Team %s does not exist in the database" +
            "but you can write a new name to correct your search or type cancel to abort. \n";
    public static final String BOARD_DOES_NOT_EXIST_MSG = "Board %s does not exist in the database " +
            "but you can write a new board name to correct your search. " +
            "Please type cancel to abort or change board name: \n";
    public static final String WORKITEM_DOES_NOT_EXIST_MSG = "Workitem with ID %s does not exist in the database " +
            "but you can write a new ID name to correct your search. " +
            "Please type cancel to abort or change the ID number: \n";
    public static final String NUMBER_ASSIGNEDWORK = "What number of assigned work to be removed?";
    public static final String LIST_ASSIGNEDWORK = "%s has the following assigned work: \n" +
            "%s";
    public static final String ENTER_BUG_STORY_FEEDBACK = "Wrong input! Please enter bug, story or feedback " +
            "or type cancel to abort.";
    public static final String TYPE_ANOTHER_COMMAND = "Please type command.";
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments!";
    public static final String PERSONSLIST_IS_EMPTY = "Persons list is empty!";
    public static final String MEMBERSLIST_IS_EMPTY = "Persons list is empty!";
    public static final String WORKITEMSLIST_IS_EMPTY = "Work items list is empty!";
    public static final String PERSONSLIST_INCLUDES = "Persons list includes: \n%s";
    public static final String TEAM_CREATED_MSG = "Team %s was created!";
    public static final String WORK_ADDED_MSG = "Work %s was added to %s!";
    public static final String WORK_NOT_EXIST_MSG = "Work with number %d does not exist!";
    public static final String WORK_UNASSIGNED = "Work with number %d was unassigned from %s!";
    public static final String MEMBER_ADDED_MSG = "%s has joined team %s!";
    public static final String TEAMSLIST_IS_EMPTY = "Teams list is empty!";
    public static final String TEAMSLIST_INCLUDES = "Teams list includes: \n%s";
    public static final String BOARD_CREATED_MSG = "Board %s was created!";
    public static final String BOARD_DOES_NOT_EXIST_ERROR_MSG = "Board %s does not exist!";
    public static final String BOARD_ADDED_SUCCESS_MESSAGE = "Board %s has been successfully added to Team %s!";
    public static final String MEMBERSLIST_INCLUDES = "Members list includes: \n%s";
    public static final String SHOW_ACTIVITY_HISTORY_QUESTION = "Show activity history of a person, a team, or a board?";
    public static final String HISTORY_UNAVAILABLE = "Activity history is available only for persons, teams or boards! " +
            "Type a new command:";
    public static final String BUG_CREATED_SUCCESS_MESSAGE = "Bug %s was created!";
    public static final String STORY_CREATED_SUCCESS_MESSAGE = "Story %s was created!";
    public static final String FEEDBACK_CREATED_SUCCESS_MESSAGE = "Feedback %s was created!";
    public static final String FAILED_TO_PARSE_COMMAND_PARAMETERS = "Failed to parse command parameters";
    public static final String WHICH_TEAM = "Which team?";
    public static final String WHICH = "Which %s?\n";
    public static final String WHAT = "%s name?\n";
    public static final String WHICH_PERSON = "Which person?";
    public static final String WORKITEM_DOES_NOT_EXIST_ERROR_MSG = "Workitem %s with id %d does not exist!";
    public static final String WORK_ITEM_SUCCESS_REMOVAL_MESSAGE = "Workitem %s with id %d successfully removed";
    public static final String WORKITEM_DOES_NOT_EXIST_IN_BOARD_MSG = "Workitem %s with id %d does not exist in board %s";
    public static final String WORKITEM_ID_DOES_NOT_MATCH_NAME_MSG = "The Workitem id does not match the name";
    public static final String STATUS_SUCCESSFULLY_CHANGED_MSG = "The status of %s was successfully changed to %s";
    public static final String PRIORITY_SUCCESSFULLY_CHANGED_MSG = "The priority of %s was successfully changed to %s";
    public static final String RATING_SUCCESSFULLY_CHANGED_MSG = "The rating of %s was successfully changed to %s";
    public static final String SEVERITY_SUCCESSFULLY_CHANGED_MSG = "The severity of %s was successfully changed to %s";
    public static final String SIZE_SUCCESSFULLY_CHANGED_MSG = "The size of %s was successfully changed to %s";
    public static final String ITEM_DOES_NOT_EXIST_ERROR_MSG = "Item %s does not exist!";
    public static final String NOT_A_MEMBER_OF_THIS_TEAM = "%s is not a member of this team! " +
            "Add a new team name or type cancel to abort:\n";
    public static final String MEMBER_REMOVED_FROM_TEAM = "%s was removed from team %s!";
    public static final String MEMBER_OF_TEAMS = "%s is a member of: \n";
    public static final String WHAT_NAME = "What name?";
    public static final String ITEM_DOES_NOT_EXIST_IN_ANOTHER_MSG = "Item %s does not exist in %s!";
    public static final String ADD_COMMENT_MSG = "Please write the comment you wish to add to %s in %s";
    public static final String COMMENT_SUCCESSFULLY_ADDED_MSG = "The comment was successfully added to %s";
    public static final String BUG = "bug";
    public static final String STORY = "story";
    public static final String FEEDBACK = "feedback";
    public static final String WORKITEM_NOT_BUG_OR_STORY = "The workitem is not a bug or a story";
    public static final String WORKITEM_NOT_FEEDBACK = "The workitem is not a feedback";
    public static final String WORKITEM_NOT_BUG = "The workitem is not a bug";
    public static final String WORKITEM_NOT_STORY = "The workitem is not a story";
    public static final String CHOOSE_WORKITEM_TYPE = "Choose one of the following filters: Bug / Story / Feedback:";
    public static final String CHOOSE_WORKITEM_STATUS_FILTER = "Choose one of the following filters: Active / Fixed / " +
            "NotDone / InProgress / Done / New " +
            "/ Unscheduled / Scheduled";
    public static final String CHOOSE_WORKITEM_ASSIGNEE = "Choose a person that you wish to filter by";
    public static final String WHAT_WILL_IT_BE = "What will the %s be?";
    public static final String SIZE = "Size";
    public static final String SEVERITY = "Severity";
    public static final String PRIORITY = "Priority";
    public static final String STATUS = "Status";
    public static final String RATING = "Rating";
    public static final String SIZES = "Large / Medium / Small";
    public static final String SEVERITIES = "Critical / Major / Minor";
    public static final String PRIORITIES = "High / Medium / Low";

}
