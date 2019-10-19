package workitems.models;

import enums.Status;
import workitems.contracts.WorkItems;
import functionals.models.ValidationHelper;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static workitems.Constants.EMPTY_COMMENT_LIST_MSG;
import static workitems.Constants.INVALID_ENUM_ERROR_MSG;

public abstract class WorkItemsImpl implements WorkItems {
    private static final int TITLE_LENGTH_MIN_VALUE = 10;
    private static final int TITLE_LENGTH_MAX_VALUE = 50;
    private static final int DESCRIPTION_LENGTH_MIN_VALUE = 10;
    private static final int DESCRIPTION_LENGTH_MAX_VALUE = 500;
    private static final String ITEM_CREATED = "%s created!";

    private int id;
    private static int currentId;
    private String title;
    private String description;
    private Status status;
    private List<String> comments;
    private List<String> history;

    static {
        currentId = 1;
    }

    //making the constructor protected, so that you cannot create a WorkItemsImpl object (can be created only through sub-classes)
    protected WorkItemsImpl(String title, String description) {
        setId();
        setTitle(title);
        setDescription(description);
        comments = new ArrayList<>();
        history = new ArrayList<>();
        addHistory(String.format(ITEM_CREATED, getItemType()));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public abstract EnumSet<Status> getStatusList();

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public List<String> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public String showComments() {
        StringBuilder str = new StringBuilder();
        if (getComments().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_COMMENT_LIST_MSG);
        }
        for (String element : getComments()) {
            str.append(element + "\n");
        }
        return str.toString().trim();
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public void setStatus(Status status) {
        if (!getStatusList().contains(status)) {
            throw new IllegalArgumentException(String.format(INVALID_ENUM_ERROR_MSG, status, getItemType()));
        }
        this.status = status;
    }

    @Override
    public void addComment(String comment) {
        if (comment.isEmpty()) {
            throw new IllegalArgumentException("Cannot add an empty comment");
        }
        comments.add(comment);
    }

    @Override
    public void addHistory(String history) {
        this.history.add(history);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s with ID:%d\n" +
                        "Title: %s\n" +
                        "Description: %s\n", getItemType(),
                getId(), getTitle(), getDescription()));
        if (getStatus() != null) {
            str.append(String.format("Status: %s\n", getStatus()));
        }
        return str.toString();

    }

    //abstract method that will return the ItemType, so we can pass it as a parameter when an Item is created.
    public abstract String getItemType();

    //Id can be an internal parameter, so there is no need to pass it as a parameter in the constructor
    protected void setId() {
        this.id = currentId;
        currentId++;
    }

    private void setTitle(String title) {
        ValidationHelper.checkIfNull(title);
        ValidationHelper.checkStringLengthInBounds(title, TITLE_LENGTH_MIN_VALUE, TITLE_LENGTH_MAX_VALUE);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelper.checkIfNull(description);
        ValidationHelper.checkStringLengthInBounds(description, DESCRIPTION_LENGTH_MIN_VALUE, DESCRIPTION_LENGTH_MAX_VALUE);
        this.description = description;
    }
}
