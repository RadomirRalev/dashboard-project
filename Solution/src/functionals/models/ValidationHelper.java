package functionals.models;

public class ValidationHelper {
    private static final String NULL_ERROR = "Value cannot be null!";
    private static final String VALUE_OUT_OF_BOUNDS_ERROR = "Value is out of bounds!";

    public static void checkIfNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
    }

    public static void checkStringLengthInBounds(String title, int minNumber, int maxNumber) {
        if (title.length() < minNumber || title.length() > maxNumber) {
            throw new IllegalArgumentException(VALUE_OUT_OF_BOUNDS_ERROR);
        }
    }

    public static void checkNumberInBounds(int number, int minValue, int maxValue) {
        if (number < minValue || number > maxValue) {
            throw new IllegalArgumentException(VALUE_OUT_OF_BOUNDS_ERROR);
        }
    }

}
