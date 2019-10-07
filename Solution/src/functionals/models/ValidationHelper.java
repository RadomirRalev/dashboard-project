package functionals.models;

import enums.*;

public class ValidationHelper {

    private static final String NULL_ERROR = "Value cannot be null!";
    private static final String VALUE_OUT_OF_BOUNDS_ERROR = "Value is out of bounds!";

    public static void checkIfNull(String string) {
        if(string == null){
            throw new IllegalArgumentException(NULL_ERROR);
        }
    }

    public static void checkNumberInBounds(String title, int minNumber, int maxNumber) {
        if(title.length() < minNumber || title.length() > maxNumber){
            throw new IllegalArgumentException(VALUE_OUT_OF_BOUNDS_ERROR);
        }
    }
}
