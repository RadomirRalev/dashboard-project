package commands.actions;

import core.contracts.FunctionalsRepository;
import functionals.contracts.Board;
import workitems.contracts.WorkItems;

import java.util.List;
import java.util.Map;

import static commands.actions.CommandsConstants.*;

public class ValidationCommands {
    public static void validateInput(List<String> parameters, int expectedNumber) {
        if (parameters.size() != expectedNumber) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
        }
    }

    public static void checkIfNamesMatch(String name1, String name2){
        if(!name1.equals(name2)){
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
}
