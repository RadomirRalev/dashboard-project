package commands.actions;

public class CommandsConstants {
    public static final String INVALID_COMMAND = "Invalid command name: %s!";
    public static final String PERSON_CREATED_MSG = "Person %s was created!";
    public static final String PERSON_DELETED_MSG = "Person %s was deleted!";
    public static final String PERSON_EXISTS_ERROR_MSG = "Name %s already exists in the database " +
            "but you can add a family name or some identifier to create this person. " +
            "Please type cancel to abort or change name: \n";
    public static final String MEMBER_DOES_NOT_EXIST_MSG = "Member %s does not exist in the database " +
            "but you can write a new name to correct your search. " +
            "Please type cancel to abort or change name: \n";
    public static final String TEAM_DOES_NOT_EXIST_MSG = "Team %s does not exist in the database " +
            "but you can write a new team name to correct your search. " +
            "Please type cancel to abort or change team name: \n";
    public static final String BOARD_DOES_NOT_EXIST_MSG = "Board %s does not exist in the database " +
            "but you can write a new board name to correct your search. " +
            "Please type cancel to abort or change board name: \n";
    public static final String TYPE_ANOTHER_COMMAND = "Please type command.";
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments!";
    public static final String PERSONSLIST_IS_EMPTY = "Persons list is empty!";
    public static final String PERSONSLIST_INCLUDES = "Persons list includes: \n%s";
    public static final String PERSON_DOES_NOT_EXIST_ERROR_MSG = "Person %s does not exist!";
    public static final String TEAM_EXISTS_ERROR_MSG = "Team %s already exists!";
    public static final String TEAM_CREATED_MSG = "Team %s was created!";
    public static final String WORK_ADDED_MSG = "Work %s was added to %s!";
    public static final String WORK_NOT_EXIST_MSG = "Work with number %d does not exist!";
    public static final String WORK_UNASSIGNED = "Work with number %d was unassigned from %s!";
    public static final String TEAM_DOES_NOT_EXIST_ERROR_MSG = "Team %s does not exist!";
    public static final String MEMBER_ADDED_MSG = "%s has joined team %s!";
    public static final String TEAMSLIST_IS_EMPTY = "Teams list is empty!";
    public static final String TEAMSLIST_INCLUDES = "Teams list includes: \n%s";
    public static final String BOARD_EXISTS_ERROR_MSG = "Board %s already exists!";
    public static final String BOARD_CREATED_MSG = "Board %s was created!";
    public static final String BOARD_DOES_NOT_EXIST_ERROR_MSG = "Board %s does not exist!";
    public static final String BOARD_ADDED_SUCCESS_MESSAGE = "Board %s has been successfully added to Team %s!";
    public static final String MEMBERSLIST_INCLUDES = "Members list includes: \n%s";
    public static final String SHOW_ACTIVITY_HISTORY_QUESTION = "Show activity history of a member, a team, or a board?";
    public static final String WHICH_MEMBER = "Which member?";
    public static final String HISTORY_UNAVAILABLE = "Activity history is available only for members, teams or boards! " +
            "Type a new command:";
    public static final String BUG_CREATED_SUCCESS_MESSAGE = "Bug %s was created!";
    public static final String STORY_CREATED_SUCCESS_MESSAGE = "Story %s was created!";
    public static final String FEEDBACK_CREATED_SUCCESS_MESSAGE = "Feedback %s was created!";
    public static final String FAILED_TO_PARSE_COMMAND_PARAMETERS = "Failed to parse command parameters";
    public static final String WHICH_BOARD = "Which board?";
    public static final String WHICH_TEAM = "Which team?";
    public static final String WORKITEM_DOES_NOT_EXIST_ERROR_MSG = "Workitem %s with id %d does not exist!";
    public static final String WORK_ITEM_SUCCESS_REMOVAL_MESSAGE = "Workitem %s with id %d successfully removed";
    public static final String WORKITEM_DOES_NOT_EXIST_IN_BOARD_MSG = "Workitem %s with id %d does not exist in board %s";
    public static final String WORKITEM_ID_DOES_NOT_MATCH_NAME_MSG = "The Workitem id does not match the name";
    public static final String STATUS_SUCCESSFULLY_CHANGED_MSG = "The status of %s was successfully changed to %s";
}
